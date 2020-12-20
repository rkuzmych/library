package com.rkuzmych.library.repository;

import com.rkuzmych.library.domain.Author;
import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByName(String name);
    List<Book> findByGenre(Genre genre);
    List<Book> findByAuthor(Author author);
    List<Book> findByGenreAndAuthor(Genre genre, Author author);
}
