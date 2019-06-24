package com.easyjob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easyjob.model.Student;
import com.easyjob.repository.Students;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Students st;
	
	@GetMapping
	public String showLoginPage() {
		return "Login";
	}	
	
	@PostMapping
	public String loginStudent(String email, String password) {
	Student s = st.findByEmail(email);
	if( s.getEmail().equals(email) ) {
		System.out.println(s.getname());
	}
	return "Login";
	
	}
}
