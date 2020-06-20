package com.jake.demo.fatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.demo.model.Book;
import com.jake.demo.repository.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class Query {

    @Autowired
    private BookRepository bookRepository;
    
    public DataFetcher<Book> book() {
        return env -> {
            String bookId = env.getArgument("id");
            return bookRepository.findById(Integer.valueOf(bookId));
        };
    }
    
    public DataFetcher<List<Book>> allBooks() {
        return env -> bookRepository.findAll();
    }
}