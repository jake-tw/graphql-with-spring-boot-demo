package com.jake.demo.fatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import graphql.schema.DataFetcher;

@Service
public class Mutation {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public DataFetcher<Author> addAuthor() {
        return env -> {
            String name = env.getArgument("name");
            return authorRepository.save(Author.builder().name(name).build());
        };
    }

    public DataFetcher<Book> addBook() {
        return env -> {
            String name = env.getArgument("name");
            String authorId = env.getArgument("authorId");
            return bookRepository.save(
                    Book.builder()
                    .name(name)
                    .author(authorRepository.findById(Integer.valueOf(authorId)))
                    .build());
        };
    }

    public DataFetcher<Boolean> deleteAuthor() {
        return env -> {
            String id = env.getArgument("id");
            return authorRepository.delete(Integer.valueOf(id));
        };
    }

    public DataFetcher<Boolean> deleteBook() {
        return env -> {
            String id = env.getArgument("id");
            return bookRepository.delete(Integer.valueOf(id));   
        };
    }
}
