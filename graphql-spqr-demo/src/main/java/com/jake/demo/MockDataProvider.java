package com.jake.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jake.demo.model.Author;
import com.jake.demo.model.Book;

public class MockDataProvider {

    private static int authorSeq;
    private static int bookSeq;

    public static final Map<Integer, Author> mockAuthorData = new ConcurrentHashMap<Integer, Author>();

    public static final Map<Integer, Book> mockBookData = new ConcurrentHashMap<Integer, Book>();

    static {
        mockAuthorData.put(1, Author.builder().id(1).name("Joshua Bloch").build());
        mockAuthorData.put(2, Author.builder().id(2).name("Robert C. Martin").build());
        mockAuthorData.put(3, Author.builder().id(3).name("Eric Evans").build());

        mockBookData.put(1, Book.builder().id(1).name("Effective Java").author(mockAuthorData.get(1)).build());
        mockBookData.put(2, Book.builder().id(2).name("Clean Architecture: A Craftsman's Guide to Software Structure and Design").author(mockAuthorData.get(2)).build());
        mockBookData.put(3, Book.builder().id(3).name("Domain-Driven Design: Tackling Complexity in the Heart of Software").author(mockAuthorData.get(3)).build());
        mockBookData.put(4, Book.builder().id(4).name("Clean Code: A Handbook of Agile Software Craftsmanship").author(mockAuthorData.get(2)).build());

        authorSeq = mockAuthorData.size();
        bookSeq = mockAuthorData.size();
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
