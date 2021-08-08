package com.codecool.languageschool_szda.repository;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
