package com.jake.demo.fatcher;

import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.springframework.stereotype.Service;

import com.jake.demo.loader.AuthorLoader;
import com.jake.demo.model.Author;
import com.jake.demo.model.Book;

import graphql.schema.DataFetcher;

@Service
public class BookDataFetchers {
    
    public DataFetcher<CompletableFuture<Author>> author() {
        return env -> {
            Book book = env.getSource();
            Integer id = book.getAuthor().getId();
            DataLoader<Integer, Author> dataLoader = env.getDataLoader(AuthorLoader.class.getSimpleName());
            return dataLoader.load(id);
        };
    }
}
