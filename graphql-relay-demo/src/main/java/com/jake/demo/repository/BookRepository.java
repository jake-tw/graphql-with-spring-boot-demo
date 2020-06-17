package com.jake.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jake.demo.MockDataProvider;
import com.jake.demo.model.Book;

@Repository
public class BookRepository {

    public List<Book> findAll() {
        return MockDataProvider.mockBookData.values().stream().collect(Collectors.toList());
    }

}
