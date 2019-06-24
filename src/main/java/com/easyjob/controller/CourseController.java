package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.easyjob.model.Course;
import com.easyjob.repository.Courses;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private Courses cr;

	@GetMapping
	public ModelAndView showCourses() {
		ModelAndView mv = new ModelAndView("Courses");
		mv.addObject("courses", cr.findAll());
		Course c = new Course();
		mv.addObject("course", c);
		return mv;
	}


	@RequestMapping("/courses?tittleSearch={tittle}")
	public ModelAndView showSomeCourses(String tittle) {
		System.out.println(tittle);
		ModelAndView mv = new ModelAndView("Courses");
		mv.addObject("courses", cr.findByTittle(tittle));
		Course c = new Course();
		mv.addObject("course", c);
		return mv;
	}
	
	 @PostMapping
	 public String addCourse(Course c) {
		cr.save(c);
	 	return	 "redirect:/courses"; }
	 
	 @RequestMapping("/delete/{tittle}")
	 public String deleteCourse(@PathVariable String tittle, RedirectAttributes attributes) {
		if( cr.findByTittle(tittle) != null ) {
			Course c = cr.findByTittle(tittle);
			cr.delete(c.getId());
			attributes.addFlashAttribute("mensagem", "Curso deletado com sucesso.");
			return "redirect:/courses";
		}else{
			attributes.addFlashAttribute("mensagem", "Algum erro aconteceu ao apagar o curso. Favor atualizar a página.");
		}
	return "redirect:/courses"; 
	}

}
