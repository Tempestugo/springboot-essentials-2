package org.example.springboot_2.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot_2.domain.Book;
import org.example.springboot_2.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> list() {
        return ResponseEntity.ok(bookService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable long id) {
        return ResponseEntity.ok(bookService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody @Valid Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
