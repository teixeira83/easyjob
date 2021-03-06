package com.easyjob.controller;

import com.easyjob.model.Course;
import com.easyjob.repository.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/courses")
public class RestCourseController {

    @Autowired
    private Courses cr;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_RESTW') or hasAuthority('ROLE_RESTR')")
    public ResponseEntity<List<Course>> showCourses() {
        List<Course> courses = new ArrayList<>();
        courses  = cr.findAll();
        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_RESTW')")
    public ResponseEntity<?> addCourse(@RequestBody Course c) {
        cr.save(c);
        return  new ResponseEntity<>(c,HttpStatus.CREATED);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_RESTW')")
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
