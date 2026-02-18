package org.example.springboot_2.repository;

import org.assertj.core.api.Assertions;
import org.example.springboot_2.domain.Book;
import org.example.springboot_2.util.BookCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Book Repository")
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("save creates book when Sucessful")
    void save_PersistBook_WhenSucessful() {
        Book book = BookCreator.createBookToBeSaved();
        Book savedBook = this.bookRepository.save(book);

        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isNotNull();
        Assertions.assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
    }


    @Test
    @DisplayName("update updates book when Sucessful")
    void save_UpdatesBook_WhenSucessful() {
        Book book = BookCreator.createBookToBeSaved();
        Book savedBook = this.bookRepository.save(book);
        savedBook.setTitle("Crônicas");
        Book bookUpdated = this.bookRepository.save(savedBook);

        Assertions.assertThat(bookUpdated).isNotNull();
        Assertions.assertThat(bookUpdated.getTitle()).isEqualTo(book.getTitle());

}

    @Test
    @DisplayName("Find By Title returns list of books when Sucessful")
    void findByTitle_ReturnsListOfBooks_WhenSucessful(){
        Book book = BookCreator.createBookToBeSaved();
        Book savedBook = this.bookRepository.save(book);

        String title = savedBook.getTitle();
        List<Book> booksList = this.bookRepository.findByTitle(title);

        Assertions.assertThat(booksList)
                .isNotEmpty()
                .contains(savedBook);
    }

    @Test
    @DisplayName("Delete removes book when Sucessful")
    void delete_RemovesBook_WhenSucessful() {
        Book book = BookCreator.createBookToBeSaved();
        Book savedBook = this.bookRepository.save(book);

        this.bookRepository.delete(savedBook);
        Optional<Book> byId = this.bookRepository.findById(savedBook.getId());
        Assertions.assertThat(byId).isEmpty();
    }

    @Test
    @DisplayName("Find By Title returns empty list when no book is found")
    void findByTitle_ReturnsEmptyList_WhenBookIsNotFound(){
        List<Book> booksList = this.bookRepository.findByTitle("title");
        Assertions.assertThat(booksList).isEmpty();
    }

    @Test
    @DisplayName("Save Throws ConstraintViolationException when title is empty")
    void save_ThrowsContraintViolationException(){
        Book book = new Book();
        Assertions.assertThatThrownBy(() -> this.bookRepository.save(book))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
