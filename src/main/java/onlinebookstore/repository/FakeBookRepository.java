package onlinebookstore.repository;

import onlinebookstore.model.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeBookRepository implements BookRepository {

    private Map<Long, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }
}
