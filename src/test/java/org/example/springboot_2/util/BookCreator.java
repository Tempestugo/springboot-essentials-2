package org.example.springboot_2.util;

import org.example.springboot_2.domain.Book;

public class BookCreator {
    public static Book createBookToBeSaved(){
        return Book.builder()
                .title("China")
                .author("hugo")
                .build();
    }
    public static Book createValidBook(){
        return Book.builder()
                .title("China")
                .author("hugo")
                .id(1L)
                .build();
    }
    public static Book createValidUpdatedBook(){
        return Book.builder()
                .title("China")
                .author("hugo")
                .id(1L)
                .build();
    }
}
