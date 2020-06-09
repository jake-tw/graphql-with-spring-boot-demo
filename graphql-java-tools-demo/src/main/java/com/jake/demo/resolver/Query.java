package com.jake.demo.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.exception.QueryNotFoundException;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class Query implements GraphQLQueryResolver {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    public List<Author> allAuthors() {
        return authorRepository.findAll();
    }

    public Book book(int id) {
        Book r = bookRepository.findById(id);
        if (r == null) {
            throw new QueryNotFoundException("Book not found.");
        }
        return bookRepository.findById(id);
    }

    public Author author(int id) {
        Author r = authorRepository.findById(id);
        if (r == null) {
            throw new QueryNotFoundException("Author not found.");
        }
        return r;
    }

    public int countBooks() {
        return bookRepository.count();
    }

    public int countAuthors() {
        return authorRepository.count();
    }
}
