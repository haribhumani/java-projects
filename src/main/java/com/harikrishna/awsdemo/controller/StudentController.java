package com.harikrishna.awsdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.harikrishna.awsdemo.dto.Student;

@RestController
public class StudentController {

	private Map<Integer, Student> studentMap = new HashMap<>();

	public StudentController() {

		Student s1 = new Student(1, "Harikrishna", "SVNE");
		Student s2 = new Student(2, "Ramya", "SIST");
		Student s3 = new Student(3, "Mokshit", "MIT");
		Student s4 = new Student(4, "Madhavi", "IIT");
		Student s5 = new Student(5, "Sree", "IIT");
		studentMap.put(s1.getId(), s1);
		studentMap.put(s2.getId(), s2);
		studentMap.put(s3.getId(), s3);
		studentMap.put(s4.getId(), s4);
		studentMap.put(s5.getId(), s5);

	}

	@GetMapping("/")
	public String hello() {
		return "Welcome to AWS Spring Boot demo";
	}

	@GetMapping("/students")
	public List<Student> students() {
		return studentMap.values().stream().collect(Collectors.toList());
	}

	@PostMapping("/add")
	public Student addstudent(@RequestBody Student student) {
		int id = student.getId() == 0 ? studentMap.size() + 1 : student.getId();
		Student s = new Student(id, student.getName(), student.getCollege());
		studentMap.put(id, s);
		return s;
	}

	@PutMapping("/update/{id}")
	public Student update(@PathVariable("id") int id, @RequestBody Student student) throws Exception {

		if (studentMap.containsKey(id)) {
			Student s = studentMap.get(id);
			s.setName(student.getName());
			s.setCollege(student.getCollege());
			return s;
		}
		throw new Exception(String.format("User with id %s not found", id));

	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {

		if (studentMap.containsKey(id)) {
			studentMap.remove(id);
			return String.format("Successfully deleted the user with id %s", id);
		}
		return String.format("User with id %s not found", id);
	}

}
