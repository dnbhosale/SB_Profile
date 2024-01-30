package com.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		return ResponseEntity.ok(student);
	}

	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);
		URI location = URI.create("/api/students/" + savedStudent.getId());
		return ResponseEntity.created(location).body(savedStudent);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseEntity.noContent().build();
	}
}
