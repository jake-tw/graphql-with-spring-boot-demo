package com.jake.demo.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jake.demo.model.Author;
import com.jake.demo.model.Book;
import com.jake.demo.repository.AuthorRepository;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class BookResolver {

    @Autowired
    private AuthorRepository authorRepository;

    @GraphQLQuery(name = "author")
    public Author author(@GraphQLContext Book book) {
        return authorRepository.findById(book.getAuthor().getId());
    }
}
