package com.erin.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(ModelAndView mav) {
		return "home";
	}
	
	@RequestMapping("/login")
	public String loginForm() {
	  return "login-form";
	}

	@RequestMapping("/signup")
	public String signUpForm() {
	  return "signup";
	}
	
	@ResponseBody
	@RequestMapping("/logout")
	public String logout() {
	  return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/test")
	public String test() {
		return "OK";
	}

	@ResponseBody
	@RequestMapping("/adminOnly")
	public String adminOnly() {
		return "Secret Page";
	}
}