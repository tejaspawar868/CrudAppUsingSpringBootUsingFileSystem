package com.rest.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.demo.model.Student;

@Service
public class StudentService {

	private final String DIRECTORY = "students";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StudentService() {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    
    public Student saveStudent(Student student) throws IOException {
        File studentFile = new File(DIRECTORY, student.getId() + ".json");
        objectMapper.writeValue(studentFile, student);
        return student;
    }

   
    public Student getStudentById(int id) throws IOException {
        File studentFile = new File(DIRECTORY, id + ".json");
        if (studentFile.exists()) {
            return objectMapper.readValue(studentFile, Student.class);
        }
        return null;
    }

    
    public List<Student> getAllStudents() throws IOException {
        List<Student> students = new ArrayList<>();
        File dir = new File(DIRECTORY);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                Student student = objectMapper.readValue(file, Student.class);
                students.add(student);
            }
        }
        return students;
    }

   
    public boolean deleteStudent(int id) {
        File studentFile = new File(DIRECTORY, id + ".json");
        return studentFile.delete();
    }
}
