package com.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyjob.model.Student;

public interface Students extends JpaRepository<Student, String>{

}
