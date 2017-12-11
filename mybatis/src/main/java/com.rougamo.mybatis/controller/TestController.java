package com.rougamo.mybatis.controller;

import com.rougamo.mybatis.mapper.StudentMapper;
import com.rougamo.mybatis.mapper.TeacherMappber;
import com.rougamo.mybatis.pojo.Student;
import com.rougamo.mybatis.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    TeacherMappber teacherMappber;

    @Autowired
    StudentMapper studentMapper;

    @GetMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Teacher> getTechers() {
        List<Teacher> teachers = teacherMappber.getTeachers();
        System.out.println(teachers.size());
        return teachers;
    }

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Student> getStudents() {
        List<Student> students = studentMapper.getStuents(2);
        System.out.println(students.size());
        return students;
    }
}
