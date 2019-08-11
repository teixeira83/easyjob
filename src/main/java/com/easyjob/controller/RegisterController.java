package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyjob.model.Student;
import com.easyjob.repository.Students;

import javax.validation.Valid;

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
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String resgisterStudent(@Valid Student s,
								   BindingResult bindingResult,
								   RedirectAttributes attributes) {
		if(bindingResult.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Favor verificar os campos digitados");
			return "redirect:/register";
		}else{
		st.save(s);
		attributes.addFlashAttribute("mensagem", "Registro realizado com sucesso");
		return "redirect:/register";
		}
	}
}
