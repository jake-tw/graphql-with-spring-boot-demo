package com.jake.demo.fatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.exception.QueryNotFoundException;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import graphql.schema.DataFetcher;

@Controller
public class Query {
    
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public DataFetcher<List<Book>> allBooks() {
        return dataFetchingEnvironment -> bookRepository.findAll();
    }

    public DataFetcher<List<Author>> allAuthors() {
        return dataFetchingEnvironment -> authorRepository.findAll();
    }

    public DataFetcher<Book> book() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            Book r = bookRepository.findById(Integer.valueOf(bookId));
            if (r == null) {
                throw new QueryNotFoundException("Book not found.");
            }
            return r;
        };
    }

    public DataFetcher<Author> author() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            Author r = authorRepository.findById(Integer.valueOf(authorId));
            if (r == null) {
                throw new QueryNotFoundException("Author not found.");
            }
            return r;
        };
    }

    public DataFetcher<Integer> countBooks() {
        return dataFetchingEnvironment -> Integer.valueOf(bookRepository.count());
    }

    public DataFetcher<Integer> countAuthors() {
        return dataFetchingEnvironment -> Integer.valueOf(authorRepository.count());
    }
}