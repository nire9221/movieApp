package com.erin.movie.userManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.erin.movie.userManagement.dto.UserDto;
import com.erin.movie.userManagement.entity.User;
import com.erin.movie.userManagement.service.UserService;

//@RestController
@Controller
public class UserController {

	@Autowired
	private UserService userService;

	// display signup page
	@GetMapping("/signup")
	public String createUserForm(Model model) {
		model.addAttribute("userSignup", new UserDto());
		return "signup";
	}

	// validate sign up
	@PostMapping("/signup")
	public String createUser(@Valid UserDto userDto, Model model, BindingResult result) {
		
		String resultMessage = "";
		boolean isAllValidate = true;
		
		if (result.hasErrors()) {
			isAllValidate = false;
			resultMessage += "sign in failed";
			return "signup";
		}
		
		resultMessage += "sign up success";
		model.addAttribute("isSuccess", isAllValidate);
		model.addAttribute("resultMsg", resultMessage);
	
		userService.addUser(userDto);

		return "signup-result"; // "redirect:/";
	}

	@GetMapping("/mypage/{userId}")
	public String mypage(Model model) {
		model.addAttribute("userInfo", new UserDto());

		return "mypage";
	}

}