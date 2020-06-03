package com.jake.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jake.demo.model.Author;

@Repository
public class AuthorRepository {

    public Author findById(int id) {
        return MockDataProvider.MOCK_AUTHOR_DATA.get(id);
    }

    public List<Author> findAll() {
        List<Author> r = new ArrayList<>();
        MockDataProvider.MOCK_AUTHOR_DATA.entrySet().forEach(entry -> r.add(entry.getValue()));
        return r;
    }

    public int count() {
        return MockDataProvider.MOCK_AUTHOR_DATA.size();
    }

    public Author save(Author author) {
        MockDataProvider.MOCK_AUTHOR_DATA.put(author.getId(), author);
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
