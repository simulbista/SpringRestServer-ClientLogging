package com.humber.eap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humber.eap.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
