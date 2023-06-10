package com.harikrishna.awsdemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harikrishna.awsdemo.dto.Student;

@RestController
public class StudentController {

	private List<Student> students = new ArrayList<>();

	public StudentController() {

		Student s1 = new Student(1, "Harikrishna", "SVNE");
		Student s2 = new Student(2, "Ramya", "SIST");
		Student s3 = new Student(3, "Mokshit", "MIT");
		Student s4 = new Student(4, "Madhavi", "IIT");
		Student s5 = new Student(5, "Sree", "IIT");

		students.addAll(Arrays.asList(s1, s2, s3, s4, s5));

	}

	@GetMapping("/")
	public String hello() {
		return "Welcome to AWS Spring Boot demo";
	}

	@GetMapping("/students")
	public List<Student> students() {
		return students;
	}

	@PostMapping("/add")
	public Student addstudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("college") String college) {
		Student s = new Student(id, name, college);
		students.add(s);
		return s;
	}

}
