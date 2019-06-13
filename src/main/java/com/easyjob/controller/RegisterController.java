package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easyjob.model.Student;
import com.easyjob.repository.Students;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private Students st;
	
	/*
	 * @GetMapping("/register") public String showRegisterPage() { return
	 * "Register"; }
	 */
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Register");
		Student s = new Student();
		mv.addObject("student", s);
		return(mv);
	}
	
	@PostMapping
	public String resgisterStudent(Student s) {
		st.save(s);
		return "redirect:/login";
	}
}
