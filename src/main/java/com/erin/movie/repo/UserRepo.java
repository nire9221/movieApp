package com.erin.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erin.movie.dto.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
