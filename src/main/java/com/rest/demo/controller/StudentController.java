package com.rest.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.model.Student;
import com.rest.demo.service.StudentService;

@RestController
@RequestMapping("/std")
public class StudentController {
	 @Autowired
	    private StudentService stdservice;

	   
	    @PostMapping
	    public Student saveStudent(@RequestBody Student student) throws IOException {
	        return stdservice.saveStudent(student);
	    }

	 
	    @GetMapping("/{id}")
	    public Student getStudentById(@PathVariable int id) throws IOException {
	        return stdservice.getStudentById(id);
	    }

	   
	    @GetMapping
	    public List<Student> getAllStudents() throws IOException {
	        return stdservice.getAllStudents();
	    }

	    
	    @DeleteMapping("/{id}")
	    public String deleteStudent(@PathVariable int id) {
	        boolean isDeleted = stdservice.deleteStudent(id);
	        return isDeleted ? "Student deleted successfully" : "Student not found";
	    }
}
