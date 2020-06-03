package com.jake.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jake.demo.model.Book;

@Repository
public class BookRepository {

	public Book findById(int id) {
		return MockDataProvider.MOCK_BOOK_DATA.get(id);
	}

    public List<Book> findAll() {
		List<Book> r = new ArrayList<>();
		MockDataProvider.MOCK_BOOK_DATA.entrySet().forEach(e -> r.add(e.getValue()));
		return r;
	}

	public int count() {
		return MockDataProvider.MOCK_BOOK_DATA.size();
	}

	public Book save(Book book) {
		MockDataProvider.MOCK_BOOK_DATA.put(book.getId(), book);
		return book;
	}
	
	public boolean delete(int id) {
		if (MockDataProvider.MOCK_BOOK_DATA.containsKey(id)) {
			MockDataProvider.MOCK_BOOK_DATA.remove(id);
			return true;
		} else {
			return false;
		}
	}
}
