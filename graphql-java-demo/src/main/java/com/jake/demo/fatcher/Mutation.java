package com.jake.demo.fatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;
import com.jake.demo.repository.BookRepository;
import com.jake.demo.repository.MockDataProvider.AuthorSeq;
import com.jake.demo.repository.MockDataProvider.BookSeq;

import graphql.schema.DataFetcher;

@Controller
public class Mutation {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public DataFetcher<Author> addAuthor() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            return authorRepository.save(Author.builder().id(AuthorSeq.nextval()).name(name).build());
        };
    }

    public DataFetcher<Book> addBook() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            String authorId = dataFetchingEnvironment.getArgument("authorId");
            return bookRepository.save(Book.builder().id(BookSeq.nextval()).name(name).author(authorRepository.findById(Integer.valueOf(authorId))).build());
        };
    }

    public DataFetcher<Boolean> deleteAuthor() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return authorRepository.delete(Integer.valueOf(id));
        };
    }

    public DataFetcher<Boolean> deleteBook() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return bookRepository.delete(Integer.valueOf(id));   
        };
    }
}
