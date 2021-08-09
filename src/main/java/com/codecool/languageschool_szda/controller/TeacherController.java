package com.codecool.languageschool_szda.controller;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Teacher;
import com.codecool.languageschool_szda.service.CourseService;
import com.codecool.languageschool_szda.service.LanguageService;
import com.codecool.languageschool_szda.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable("id") Long id) {
        return teacherService.getById(id);
    }

    @PutMapping
    public Teacher update(@RequestBody Teacher teacher) {
        return teacherService.update(teacher);
    }

    @PostMapping
    public Teacher add(@RequestBody Teacher teacher) {
        return teacherService.add(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        teacherService.deleteById(id);
    }
}
