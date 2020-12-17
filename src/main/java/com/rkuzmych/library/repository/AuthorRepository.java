package com.rkuzmych.library.repository;

import com.rkuzmych.library.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
