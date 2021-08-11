package com.codecool.languageschool_szda.service;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Teacher;
import com.codecool.languageschool_szda.repository.CourseRepository;
import com.codecool.languageschool_szda.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository teacherRepository;

    public Teacher getById(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) return optionalTeacher.get();
        else throw new RuntimeException("Teacher does not exist");
    }

    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    public Teacher update(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }
}
