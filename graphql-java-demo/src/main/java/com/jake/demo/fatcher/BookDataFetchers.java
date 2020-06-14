package com.jake.demo.fatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;

import graphql.schema.DataFetcher;

@Controller
public class BookDataFetchers {
    
    @Autowired
    private AuthorRepository authorRepository;

    public DataFetcher<Author> author() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            return authorRepository.findById(book.getAuthor().getId());
        };
    }
}
