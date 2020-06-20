package com.jake.demo.fatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.demo.exception.QueryNotFoundException;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class Query {
    
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public DataFetcher<List<Book>> allBooks() {
        return env -> bookRepository.findAll();
    }

    public DataFetcher<List<Author>> allAuthors() {
        return env -> authorRepository.findAll();
    }

    public DataFetcher<Book> book() {
        return env -> {
            String bookId = env.getArgument("id");
            Book r = bookRepository.findById(Integer.valueOf(bookId));
            if (r == null) {
                throw new QueryNotFoundException("Book not found.");
            }
            return r;
        };
    }

    public DataFetcher<Author> author() {
        return env -> {
            String authorId = env.getArgument("id");
            Author r = authorRepository.findById(Integer.valueOf(authorId));
            if (r == null) {
                throw new QueryNotFoundException("Author not found.");
            }
            return r;
        };
    }

    public DataFetcher<Integer> countBooks() {
        return env -> Integer.valueOf(bookRepository.count());
    }

    public DataFetcher<Integer> countAuthors() {
        return env -> Integer.valueOf(authorRepository.count());
    }
}