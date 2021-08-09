package com.codecool.languageschool_szda.controller;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Language;
import com.codecool.languageschool_szda.service.CourseService;
import com.codecool.languageschool_szda.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){this.courseService=courseService;}

    @GetMapping
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getById(@PathVariable("id") Long id){
        return courseService.getById(id);
    }

    @PutMapping
    public Course update(@RequestBody Course course) {
        return courseService.update(course);
    }

    @PostMapping
    public Course add(@RequestBody Course course) {
        return courseService.add(course);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        courseService.deleteById(id);
    }



}
