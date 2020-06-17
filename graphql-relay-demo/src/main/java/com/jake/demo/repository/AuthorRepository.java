package com.jake.demo.repository;

import org.springframework.stereotype.Repository;

import com.jake.demo.MockDataProvider;
import com.jake.demo.model.Author;

@Repository
public class AuthorRepository {

    public Author findById(int id) {
        return MockDataProvider.mockAuthorData.get(id);
    }
}
