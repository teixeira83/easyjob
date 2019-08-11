package com.easyjob.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyjob.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Courses extends JpaRepository<Course, Long>{

	@Query("SELECT c from Course c where c.tittle like %:searchTitle%")
	List<Course> findByTittle(@Param("searchTitle") String searchTittle);

	Course findById(Long id);
}
