package com.erin.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erin.movie.dto.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long>{

}
