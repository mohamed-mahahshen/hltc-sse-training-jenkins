package onlinebookstore.repository;

import onlinebookstore.model.Book;

import java.util.Optional;

public interface BookRepository {

    Optional<Book> findById(Long id);
}

