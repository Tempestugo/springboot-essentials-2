package org.example.springboot_2.controller;

import org.assertj.core.api.Assertions;
import org.example.springboot_2.domain.Book;
import org.example.springboot_2.service.BookService;
import org.example.springboot_2.util.BookCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(bookServiceMock.listAll())
                .thenReturn(List.of(BookCreator.createValidBook()));
        
        BDDMockito.when(bookServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(BookCreator.createValidBook());

        BDDMockito.when(bookServiceMock.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(List.of(BookCreator.createValidBook()));
        
        BDDMockito.when(bookServiceMock.save(ArgumentMatchers.any(Book.class)))
                .thenReturn(BookCreator.createValidBook());
        
        BDDMockito.doNothing().when(bookServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("List returns list of books when successful")
    void list_ReturnsListOfBooks_WhenSuccessful() {
        String expectedTitle = BookCreator.createValidBook().getTitle();
        List<Book> books = bookController.list().getBody();

        Assertions.assertThat(books)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        
        Assertions.assertThat(books.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("findById returns book when successful")
    void findById_ReturnsBook_WhenSuccessful() {
        Long expectedId = BookCreator.createValidBook().getId();
        Book book = bookController.findById(1).getBody();

        Assertions.assertThat(book).isNotNull();
        Assertions.assertThat(book.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByTitle returns list of books when successful")
    void findByTitle_ReturnsListOfBooks_WhenSuccessful() {
        String expectedTitle = BookCreator.createValidBook().getTitle();
        List<Book> books = bookController.findByTitle("Harry Potter").getBody();

        Assertions.assertThat(books)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(books.get(0).getTitle()).isEqualTo(expectedTitle);
    }

    @Test
    @DisplayName("findByTitle returns empty list when book is not found")
    void findByTitle_ReturnsEmptyList_WhenBookIsNotFound() {
        BDDMockito.when(bookServiceMock.findByTitle(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Book> books = bookController.findByTitle("Harry Potter").getBody();

        Assertions.assertThat(books)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns book when successful")
    void save_ReturnsBook_WhenSuccessful() {
        Book book = bookController.save(BookCreator.createBookToBeSaved()).getBody();

        Assertions.assertThat(book).isNotNull().isEqualTo(BookCreator.createValidBook());
    }

    @Test
    @DisplayName("delete removes book when successful")
    void delete_RemovesBook_WhenSuccessful() {
        Assertions.assertThatCode(() -> bookController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = bookController.delete(1);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
