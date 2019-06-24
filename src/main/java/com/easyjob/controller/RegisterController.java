package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyjob.model.Student;
import com.easyjob.repository.Students;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private Students st;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Register");
		Student s = new Student();
		mv.addObject("student", s);
		return(mv);
	}
	
	@PostMapping
	public String resgisterStudent(Student s, RedirectAttributes attributes) {
		
		if ( (s.getEmail().equals( s.getEmailCheck() )) && (s.getpassword().equals( s.getPasswordCheck() ) ) )  {			
			st.save(s);
			attributes.addFlashAttribute("mensagem", "Registro realizado com sucesso");
			return "redirect:/register";	
		}else {
			attributes.addFlashAttribute("mensagem", "Favor verificar os campos");
		}
		return "redirect:/register";
	}
}
