package com.rkuzmych.library.repository;

import com.rkuzmych.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByType(String type);
}
