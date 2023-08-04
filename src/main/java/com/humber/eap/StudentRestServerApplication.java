package com.humber.eap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.humber.eap.models.Course;
import com.humber.eap.models.Student;
import com.humber.eap.repositories.CourseRepository;
import com.humber.eap.repositories.StudentRepository;

@SpringBootApplication
public class StudentRestServerApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(StudentRestServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// inserting courses on app start
		courseRepository.save(Course.builder().courseName("MERN").creditHours(4).build());
		
		// inserting students on app start
		studentRepository.save(Student.builder().roll(855).s_name("Simul").age(30)
				.course(Course.builder().courseName("JAVA").creditHours(4).build()).build());
		studentRepository.save(Student.builder().roll(855).s_name("Simul").age(30)
				.course(Course.builder().courseName("WEB").creditHours(4).build()).build());
		
//		// Fetch the existing course from the database
//		String courseName = "MERN";
//		Course mernCourse = courseRepository.findByCourseName(courseName);
//		if(mernCourse !=null) {
//			//assign a student a course - MERN which already exists in the course table
//			studentRepository.save(Student.builder().roll(855).s_name("Simul").age(30)
//					.course(mernCourse).build());
//		}else {
//			//assign a student a course which doesn't already exist in the course table, then create the course first in the course table
//		    Course newCourse = Course.builder()
//		            .courseName(courseName)
//		            .creditHours(3)
//		            .build();
//		    Course savedCourse = courseRepository.save(newCourse);
//			studentRepository.save(Student.builder().roll(855).s_name("Simul").age(30)
//					.course(savedCourse).build());
//		}

		studentRepository.save(Student.builder().roll(856).s_name("Palash").age(28)
				.course(Course.builder().courseName("AI & ML").creditHours(2).build()).build());
		studentRepository.save(Student.builder().roll(856).s_name("Palash").age(28)
				.course(Course.builder().courseName("DSA").creditHours(3).build()).build());

	}

}
