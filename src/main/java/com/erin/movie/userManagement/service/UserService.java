package com.erin.movie.userManagement.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erin.movie.userManagement.dto.UserDto;
import com.erin.movie.userManagement.entity.User;
import com.erin.movie.userManagement.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User findUserInfo(String username) {
		return userRepo.findOneByUsername(username);
	}

	@Transactional
	public Long addUser(UserDto userDto) {
		User userEntity = userDto.toEntity();
		userRepo.save(userEntity);
		return userEntity.getId();
	}

	public User updateUser(User user) {
		User userResult = userRepo.save(user);
		return userResult;
	}
}
