package com.humber.eap.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humber.eap.models.Student;
import com.humber.eap.services.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/student")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	@PostMapping("/student")
	public void addAStudent(@RequestBody Student student) {
		studentService.addStudent(student);
	}

	@PutMapping("/student")
	public ResponseEntity<String> updateAStudent(@RequestBody Student student) {
		try {
			studentService.updateStudent(student);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("The student with id " + student.getId() + " has been updated!");
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int id){
		try {
			studentService.deleteStudentById(id);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("The student with id " + id + " has been deleted!");
	}

}
