package com.jake.demo.loader;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jake.demo.model.Author;
import com.jake.demo.repository.AuthorRepository;

@Component
public class AuthorLoader implements BatchLoader<Integer, Author>{
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Override
    public CompletionStage<List<Author>> load(List<Integer> ids) {
        System.out.println("DataLoader execute ids: " + ids);
        return CompletableFuture.supplyAsync(() -> authorRepository.findByIds(ids));
    }
}
