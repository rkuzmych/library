package com.rkuzmych.library.repository;

import com.rkuzmych.library.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByName(String name);
}
