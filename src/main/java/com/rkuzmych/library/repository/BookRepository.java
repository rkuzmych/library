package com.rkuzmych.library.repository;

import com.rkuzmych.library.domain.Author;
import com.rkuzmych.library.domain.Book;
import com.rkuzmych.library.domain.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByName(String name, Pageable pageable);

    Page<Book> findByGenre(Genre genre, Pageable pageable);

    Page<Book> findByAuthor(Author author, Pageable pageable);

    Page<Book> findByGenreAndAuthor(Genre genre, Author author, Pageable pageable);
}
