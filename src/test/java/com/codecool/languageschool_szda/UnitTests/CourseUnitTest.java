package com.codecool.languageschool_szda.UnitTests;

import com.codecool.languageschool_szda.controller.CourseController;
import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Teacher;
import com.codecool.languageschool_szda.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseUnitTest {
    private static Teacher teacher1;
    private static Teacher teacher2;
    private static Course course1;
    private static Course course2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService service;

    @BeforeEach
    public void setup() {

        teacher1 = Teacher.builder()
                .id(1L)
                .name("Gilderoy Lockhart")
                .age(41)
                .address("Budapest")
                .languages(null)
                .build();

        teacher2 = Teacher.builder()
                .id(2L)
                .age(44)
                .address("Budapest")
                .languages(null)
                .build();

        course1 = Course.builder()
                .id(1L)
                .name("angol_alap")
                .start_date("1988")
                .end_date("1989")
                .teacher(teacher1)
                .build();

        course2 = Course.builder()
                .id(2L)
                .name("nemet_kozep")
                .start_date("1988")
                .end_date("1989")
                .teacher(teacher2)
                .build();
    }

    @Test
    public void findAll_shouldReturnListOfCourses() throws Exception {
        when(service.getAll()).thenReturn(List.of(course1, course2));

        mockMvc.perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void findTeacherById_shouldReturn() throws Exception {
        when(service.getById(1L)).thenReturn(course1);

        mockMvc.perform(get("/course/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("angol_alap")))
                .andExpect(jsonPath("$.start_date", is("1988")))
                .andExpect(jsonPath("$.end_date", is("1989")))
                .andExpect(jsonPath("$.teacher.name", is("Gilderoy Lockhart")));
    }

}
