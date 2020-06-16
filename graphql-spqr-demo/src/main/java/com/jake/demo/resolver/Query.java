package com.jake.demo.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.exception.QueryNotFoundException;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Controller
@GraphQLApi
public class Query {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GraphQLQuery(name = "allBooks")
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @GraphQLQuery(name = "allAuthors")
    public List<Author> allAuthors() {
        return authorRepository.findAll();
    }

    @GraphQLQuery(name = "book")
    public Book book(@GraphQLArgument(name = "id") int id) {
        Book r = bookRepository.findById(id);
        if (r == null) {
            throw new QueryNotFoundException("Book not found.");
        }
        return r;
    }

    @GraphQLQuery(name = "author")
    public Author author(@GraphQLArgument(name = "id") int id) {
        Author r = authorRepository.findById(id);
        if (r == null) {
            throw new QueryNotFoundException("Author not found.");
        }
        return r;
    }

    @GraphQLQuery(name = "countBooks")
    public int countBooks() {
        return bookRepository.count();
    }

    @GraphQLQuery(name = "countAuthors")
    public int countAuthors() {
        return authorRepository.count();
    }
}
