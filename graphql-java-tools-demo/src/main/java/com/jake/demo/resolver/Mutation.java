package com.jake.demo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.MockDataProvider.AuthorSeq;
import com.jake.demo.MockDataProvider.BookSeq;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Controller
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(String name) {
        return authorRepository.save(Author.builder()
                .name(name)
                .build());
    }

    public Book addBook(String name, int authorId) {
        return bookRepository.save(Book.builder()
                .name(name)
                .author(authorRepository.findById(authorId))
                .build());
    }

    public boolean deleteAuthor(int id) {
        return authorRepository.delete(id);
    }

    public boolean deleteBook(int id) {
        return bookRepository.delete(id);
    }
}
