package com.easyjob.controller;

import com.easyjob.model.Usuario;
import com.easyjob.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyjob.model.Student;
import com.easyjob.repository.Students;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Students st;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showLoginPage() {
		return "Login";
	}	
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String loginStudent(String username,
//									   String password) {
//
//		System.out.println(username);
//		System.out.println(password);
//		return "redirect:/home";
//	}
}
