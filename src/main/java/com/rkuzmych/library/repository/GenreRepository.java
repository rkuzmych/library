package com.rkuzmych.library.repository;

import com.rkuzmych.library.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Genre findByType(String type);
}
