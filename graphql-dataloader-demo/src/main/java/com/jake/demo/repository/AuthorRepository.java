package com.jake.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jake.demo.MockDataProvider;
import com.jake.demo.model.Author;

@Repository
public class AuthorRepository {

    public List<Author> findByIds(List<Integer> ids) {
        return ids.stream().map(id -> MockDataProvider.mockAuthorData.get(id)).collect(Collectors.toList());
    }
}
