package com.jake.demo.repository;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.jake.demo.model.Author;
import com.jake.demo.model.Book;

@Repository
public class MockDataProvider {

    private static int authorSeq;
    private static int bookSeq;

    public static final Map<Integer, Author> MOCK_AUTHOR_DATA = new HashMap<Integer, Author>();

    public static final Map<Integer, Book> MOCK_BOOK_DATA = new HashMap<Integer, Book>();

    @PostConstruct
    public static void init() {

        MOCK_AUTHOR_DATA.put(1, Author.builder().id(1).name("Joshua Bloch").build());
        MOCK_AUTHOR_DATA.put(2, Author.builder().id(2).name("Robert C. Martin").build());
        MOCK_AUTHOR_DATA.put(3, Author.builder().id(3).name("Eric Evans").build());

        MOCK_BOOK_DATA.put(1, Book.builder().id(1).name("Effective Java").authorId(1).build());
        MOCK_BOOK_DATA.put(2, Book.builder().id(2).name("Clean Architecture: A Craftsman's Guide to Software Structure and Design").authorId(2).build());
        MOCK_BOOK_DATA.put(3, Book.builder().id(3).name("Domain-Driven Design: Tackling Complexity in the Heart of Software").authorId(3).build());
        MOCK_BOOK_DATA.put(4, Book.builder().id(4).name("Clean Code: A Handbook of Agile Software Craftsmanship").authorId(2).build());

        authorSeq = MOCK_AUTHOR_DATA.size();
        bookSeq = MOCK_AUTHOR_DATA.size();
    }

    public static class AuthorSeq {
        public static int nextval() {
            return authorSeq += 1;
        };
    }

    public static class BookSeq {
        public static int nextval() {
            return bookSeq += 1;
        };
    }
}
