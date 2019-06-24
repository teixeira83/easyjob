package com.easyjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	public String entrar;
	
	@GetMapping("/")
	public String home() {
		return "Home";
	}
	
	@GetMapping("/aboutus")
	public String about() {
		return "About";
	}
}
