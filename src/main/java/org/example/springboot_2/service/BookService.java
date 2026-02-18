package org.example.springboot_2.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_2.domain.Book;
import org.example.springboot_2.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Book findByIdOrThrowBadRequestException(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not Found"));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(long id) {
        bookRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
