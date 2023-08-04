package com.humber.eap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.eap.models.Course;
import com.humber.eap.models.Student;
import com.humber.eap.repositories.CourseRepository;
import com.humber.eap.repositories.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	// get all students
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	// create a student
	//since there are multiple operations going on in this method i.e. creation of records in 2 tables, we need to treat all these
	//processes as a single transaction
	@Transactional
	public void addStudent(Student student) {
		//before creating a student we check if the course exists (course name cannot be duplicate in the db)
		
		//get course name from the user input
		String courseName = student.getCourse().getCourseName();
		//check if the course exists in the db
		Course foundCourse = courseRepository.findByCourseName(courseName);
		if (foundCourse != null) {
			// if the user input course already exists in the db, just add the course
			studentRepository.save(Student.builder().roll(student.getRoll()).s_name(student.getS_name())
					.age(student.getAge()).course(foundCourse).build());
		}else {
			// if the user input course doesn't exist in the db, create the course in the course table first and then add the student record
			
			//saving the course from the user to an object
		    Course newCourse = Course.builder()
		            .courseName(student.getCourse().getCourseName())
		            .creditHours(student.getCourse().getCreditHours())
		            .build();
		    //saving the course object to the course table
		    Course savedCourse = courseRepository.save(newCourse);
		    
		    //saving the student record along with the created course to the student table
			studentRepository.save(Student.builder().roll(student.getRoll()).s_name(student.getS_name()).age(student.getAge())
					.course(savedCourse).build());
		}
	}

	// update a student
	public Student updateStudent(Student student) throws Exception {
		boolean isExist = studentRepository.findAll().stream().anyMatch((s) -> s.getId() == student.getId());
		if (!isExist)
			throw new Exception("The student with id " + student.getId() + " doesn't exist!");
		return studentRepository.save(student);
	}

	// delete a student by id
	public void deleteStudentById(int id) throws Exception {
		boolean isExist = studentRepository.findAll().stream().anyMatch((s) -> s.getId() == id);
		if (!isExist)
			throw new Exception("The student with id " + id + " doesn't exist. Delete failed!");
		studentRepository.deleteById(id);
	}
}
