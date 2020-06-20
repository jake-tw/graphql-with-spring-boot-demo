package com.jake.demo.fatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.demo.model.Book;
import com.jake.demo.repository.BookRepository;

import graphql.relay.SimpleListConnection;

@Service
public class Query {
    
    @Autowired
    private BookRepository bookRepository;

    public SimpleListConnection<Book> allBooks() {
        return new SimpleListConnection<>(bookRepository.findAll());
    }
}