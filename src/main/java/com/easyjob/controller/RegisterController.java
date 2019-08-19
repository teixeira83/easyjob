package com.easyjob.controller;

import com.easyjob.model.Permission;
import com.easyjob.model.Usuario;
import com.easyjob.repository.PermissionRepository;
import com.easyjob.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyjob.model.Student;
import com.easyjob.repository.Students;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private Students studentRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired UsuarioRepository usuarioRepository;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("Register");
		Usuario u = new Usuario();
		mv.addObject("usuario", u);
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String resgisterStudent(@Valid Usuario u,
								   @RequestParam("nome") String nome,
								   BindingResult bindingResult,
								   RedirectAttributes attributes) {
		if(bindingResult.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Favor verificar os campos digitados");
			return "redirect:/register";
		}else{
			Student s = new Student();
			s.setName(nome);
			u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));

			Permission p = permissionRepository.findByNome("VIEWR");
			List<Permission> permissionList = new ArrayList<>();
			permissionList.add(p);
			u.setPermissoes(permissionList);
			usuarioRepository.save(u);
			Usuario userAfterSave = usuarioRepository.findByUsername(u.getUsername());

			s.setUsuario(userAfterSave);
			studentRepository.save(s);
			attributes.addFlashAttribute("mensagem", "Registro realizado com sucesso");
		return "redirect:/register";
		}
	}
}
