package com.group2.milestone2.domain.movie.repository;

import com.group2.milestone2.domain.movie.domain.Movie;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);
}
