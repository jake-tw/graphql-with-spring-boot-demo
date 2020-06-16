package com.jake.demo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.model.Book;
import com.jake.demo.repository.BookRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;

@Controller
public class Query implements GraphQLQueryResolver {

    @Autowired
    private BookRepository bookRepository;
    
    public Connection<Book> allBooks(int first, String after, DataFetchingEnvironment env) {
        return new SimpleListConnection<>(bookRepository.findAll()).get(env);
    }
}
