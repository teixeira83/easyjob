package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyjob.model.Course;
import com.easyjob.repository.Courses;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private Courses cr;

	@GetMapping
	public ModelAndView showCourses(Model model) {
		ModelAndView mv = new ModelAndView("Courses");
		mv.addObject("courses", cr.findAll());
		Course c = new Course();
		mv.addObject("course", c);
		return mv;
	}
	
	 @RequestMapping(method = RequestMethod.POST)
	 public String addCourse(@Valid Course c, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()){
			model.addAttribute("courses", cr.findAll());
			return "Courses";
		}
		cr.save(c);
	 	return	 "redirect:/courses"; }
	 
	 @RequestMapping("/delete/{id}")
	 public String deleteCourse(@PathVariable Long id, RedirectAttributes attributes) {
		if( cr.findById(id) != null ) {
			Course c = cr.findById(id);
			cr.delete(c.getId());
			attributes.addFlashAttribute("mensagem", "Curso deletado com sucesso.");
			return "redirect:/courses";
		}else{
			attributes.addFlashAttribute("mensagem", "Algum erro aconteceu ao apagar o curso. Favor atualizar a página.");
		}
	return "redirect:/courses"; 
	}


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchCourse(@RequestParam("courseTitle") String title){


		ModelAndView mv = new ModelAndView("Courses");
		List<Course> coursesList = new ArrayList<>();
		coursesList = cr.findByTittle(title);
		mv.addObject("courses", coursesList);
		Course c = new Course();
		mv.addObject("course", c);
		return mv;
	}

}
