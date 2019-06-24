package com.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyjob.model.Course;

public interface Courses extends JpaRepository<Course, Long>{
	Course findByTittle(String tittle);
}
