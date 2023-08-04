package com.humber.eap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humber.eap.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	// find course by course name
	Course findByCourseName(String cName);

}
