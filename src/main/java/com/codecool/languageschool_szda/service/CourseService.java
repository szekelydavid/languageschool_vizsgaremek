package com.codecool.languageschool_szda.service;
import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    private CourseRepository courseRepository;

    public Course getById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) return optionalCourse.get();
        else throw new RuntimeException("Course does not exist");
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course update(Course artist) {
        return courseRepository.save(artist);
    }

    public Course add(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

}
