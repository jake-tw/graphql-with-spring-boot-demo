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
        return MockDataProvider.MOCK_AUTHOR_DATA.get(id);
    }

    public List<Author> findAll() {
        return MockDataProvider.MOCK_AUTHOR_DATA.values().stream().collect(Collectors.toList());
    }

    public int count() {
        return MockDataProvider.MOCK_AUTHOR_DATA.size();
    }

    public Author save(Author author) {
        int id = AuthorSeq.nextval();
        author.setId(id);
        MockDataProvider.MOCK_AUTHOR_DATA.put(id, author);
        return author;
    }

    public boolean delete(int id) {
        if (MockDataProvider.MOCK_AUTHOR_DATA.containsKey(id)) {
            MockDataProvider.MOCK_AUTHOR_DATA.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
