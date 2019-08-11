package com.easyjob.controller;

import com.easyjob.model.Course;
import com.easyjob.repository.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/courses")
public class RestCourseController {

    @Autowired
    private Courses cr;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Course>> showCourses() {
        List<Course> courses = new ArrayList<>();
        courses  = cr.findAll();
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCourse(@RequestBody Course c) {
        cr.save(c);
        return  new ResponseEntity<>(c,HttpStatus.CREATED);
    }

    @RequestMapping("/delete")
    public ResponseEntity<?> deleteCourse(@RequestParam("courseId") Long id) {
        if( cr.findById(id) != null ) {
            Course c = cr.findById(id);
            cr.delete(c.getId());

            return new ResponseEntity<>(c, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Curso não encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
