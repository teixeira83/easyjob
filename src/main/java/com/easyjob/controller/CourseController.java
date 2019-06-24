package com.easyjob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.easyjob.model.Course;
import com.easyjob.repository.Courses;

@Controller
public class CourseController {
	
	@Autowired
	private Courses cr; 
	
	@GetMapping("/courses")
	public ModelAndView showCourses(){
		ModelAndView mv = new ModelAndView("Courses");
		mv.addObject("courses", cr.findAll());
		Course c = new Course();
		mv.addObject("convidado", c);
		return mv;
	}
}
