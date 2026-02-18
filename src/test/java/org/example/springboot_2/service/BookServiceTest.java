package org.example.springboot_2.service;

import org.assertj.core.api.Assertions;
import org.example.springboot_2.domain.Book;
import org.example.springboot_2.mapper.AnimeMapper;
import org.example.springboot_2.repository.AnimeRepository;
import org.example.springboot_2.repository.BookRepository;
import org.example.springboot_2.util.AnimeCreator;
import org.example.springboot_2.util.BookCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        PageImpl<Book> page = new PageImpl<>(List.of(BookCreator.createValidBook()));
        BDDMockito.when(bookRepository.findAll())
                .thenReturn(List.of(BookCreator.createValidBook()));

        BDDMockito.when(bookRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(BookCreator.createValidBook()));
    }

    @Test
    @DisplayName("listAll returns list of books when successful")
    void listAll() {
        String expectedTitle = BookCreator.createValidBook().getTitle();
        List<Book> books = bookService.listAll();

        Assertions.assertThat(books).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(expectedTitle).isEqualTo(books.get(0).getTitle());

    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns book when successful")
    void findByTitle() {
        String expectedTitle = BookCreator.createValidBook().getTitle();
        List<Book> books = bookService.findByTitle("Harry Potter");

        Assertions.assertThat(books).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(expectedTitle).isEqualTo(books.get(0).getTitle());
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws ResponseStatusException when book is not found")
    void findByIdOrThrowBadRequestException() {
        BDDMockito.when(bookRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(()-> bookService.findByIdOrThrowBadRequestException(1));

    }

    @Test
    @DisplayName("save returns book when successful")
    void save_ReturnsBook_WhenSuccessful() {
        Book book = bookService.save(BookCreator.createBookToBeSaved());
        Assertions.assertThat(book).isNotNull().isEqualTo(BookCreator.createValidBook());

    }

    @Test
    @DisplayName("delete removes book when successful")
    void delete() {
        Assertions.assertThatCode(() -> bookService.delete(1))
                .doesNotThrowAnyException();

    }
}