package com.jake.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jake.demo.MockDataProvider;
import com.jake.demo.MockDataProvider.AuthorSeq;
import com.jake.demo.model.Author;

@Repository
public class AuthorRepository {

    public Author findById(int id) {
        return MockDataProvider.mockAuthorData.get(id);
    }

    public List<Author> findAll() {
        return MockDataProvider.mockAuthorData.values().stream().collect(Collectors.toList());
    }

    public int count() {
        return MockDataProvider.mockAuthorData.size();
    }

    public Author save(Author author) {
        int id = AuthorSeq.nextval();
        author.setId(id);
        MockDataProvider.mockAuthorData.put(id, author);
        return author;
    }

    public boolean delete(int id) {
        if (MockDataProvider.mockAuthorData.containsKey(id)) {
            MockDataProvider.mockAuthorData.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
