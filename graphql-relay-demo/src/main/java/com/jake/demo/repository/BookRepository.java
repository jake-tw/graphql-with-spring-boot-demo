package com.jake.demo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jake.demo.MockDataProvider;
import com.jake.demo.MockDataProvider.BookSeq;
import com.jake.demo.model.Book;

@Repository
public class BookRepository {

    public Book findById(int id) {
        return MockDataProvider.mockBookData.get(id);
    }

    public List<Book> findAll() {
        return MockDataProvider.mockBookData.values().stream().collect(Collectors.toList());
    }

    public int count() {
        return MockDataProvider.mockBookData.size();
    }

    public Book save(Book book) {
        int id = BookSeq.nextval();
        book.setId(id);
        MockDataProvider.mockBookData.put(id, book);
        return book;
    }

    public boolean delete(int id) {
        if (MockDataProvider.mockBookData.containsKey(id)) {
            MockDataProvider.mockBookData.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
