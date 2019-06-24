package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String loginStudent(String email, String password, RedirectAttributes attributes) {		
	if( st.findByEmail(email) != null ) {
		Student s = st.findByEmail(email);
		if( (s.getEmail().equals(email)) && (s.getpassword().equals(password)) ) {
			System.out.println(s.getname());
			attributes.addFlashAttribute("mensagem", "Logado com sucesso.");
			return "redirect:/login";
		}else {
			attributes.addFlashAttribute("mensagem", "Senha Incorreta.");
		}
	}else {
		attributes.addFlashAttribute("mensagem", "Usuário não encontrado.");
	}
	return "redirect:/login";
	}
}
