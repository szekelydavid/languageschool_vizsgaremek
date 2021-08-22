package com.codecool.languageschool_szda.integration;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)

@ActiveProfiles({"test"})

public class CourseIntegrationTest {

    @LocalServerPort
    private int port;

    private String baseUrl;


    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/course";
    }

    @Test
    public void addNewCourse_emptyDatabase_shouldReturnSameCourse() {

        /*
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Teacher testTeacherOne = new Teacher();
        HttpEntity<Course> httpEntity = new HttpEntity<>(
                new Course(null,"angol_halado", "2001","2002",testTeacherOne),headers
        );

        Course testCourse = new Course(null,"angol_halado", "2001","2002",testTeacherOne);
        // ! hiba a postolással
        testRestTemplate.postForEntity(this.baseUrl, httpEntity, Course.class);
        ResponseEntity<Course[]> responseCourse = testRestTemplate.getForEntity(baseUrl,Course[].class);
        List<Course> courseLista = Arrays.asList(responseCourse.getBody());
        Course getResult = testRestTemplate.getForObject(this.baseUrl+"/1", Course.class);
        Assertions.assertEquals(testCourse.getName(), getResult.getName());
        */
        /*
        Teacher testTeacherOne = new Teacher();
        Course testCourse = new Course(null,"angol_halado", "2001","2002",testTeacherOne);
        Course result = testRestTemplate.postForObject(baseUrl, testCourse, Course.class);
        assertEquals(testCourse.getName(), result.getName());
        */
        Teacher testTeacherOne = new Teacher();
        Course testCourse = new Course((Long)null,"angol_halado", "2001","2002",testTeacherOne);
        testCourse = testRestTemplate.postForObject(this.baseUrl, testCourse, Course.class);
        Course result = testRestTemplate.getForObject(this.baseUrl + "/" + testCourse.getId(), Course.class);
        Assertions.assertEquals(testCourse.getId(), result.getId());

    }




    @Test
    public void getCourse_emptyDatabase_returnsEmptyList() {
        List<Course> songList = List.of((Course[])this.testRestTemplate.getForObject(this.baseUrl, Course[].class));
        Assertions.assertEquals(0, songList.size());
    }

    @Test
    public void getCourseById_withOnePostedCourse_returnsCourseWithSameId() {
        Teacher testTeacherOne = new Teacher();
        Course testCourse = new Course((Long)null,"angol_halado", "2001","2002",testTeacherOne);
        testCourse = testRestTemplate.postForObject(this.baseUrl, testCourse, Course.class);
        Course result = testRestTemplate.getForObject(this.baseUrl + "/" + testCourse.getId(), Course.class);
        Assertions.assertEquals(testCourse.getId(), result.getId());
    }

    @Test
    public void updateCourse_withOnePostedCourse_returnsUpdatedCourse() {
        Teacher testTeacherOne = new Teacher();
        Course testCourse = new Course((Long)null,"angol_halado", "2001","2002",testTeacherOne);
        testCourse = testRestTemplate.postForObject(this.baseUrl, testCourse, Course.class, new Object[0]);
        testCourse.setName("Updated name");
        this.testRestTemplate.put(this.baseUrl, testCourse, new Object[0]);
        Course updatedCourse = testRestTemplate.getForObject(this.baseUrl + "/" + testCourse.getId(), Course.class);
        Assertions.assertEquals("Updated name", updatedCourse.getName());
    }

    @Test
    public void deleteCourseById_withSomePostedCourses_getAllShouldReturnRemainingCourse() {
        Teacher testTeacherOne = new Teacher();
        Course testCourseOne = new Course(null,"angol_halado", "2002","2002",testTeacherOne);
        Course testCourseTwo = new Course(null,"nemet_kozep", "2000","2001",testTeacherOne);
        Course testCourseThree = new Course(null,"japan", "2000","2003",testTeacherOne);
        List<Course> testCourses = new ArrayList();
        testCourses.add(testCourseOne);
        testCourses.add(testCourseTwo);
        testCourses.add(testCourseThree);
        testCourses.forEach((testCourse) -> {
            testCourse.setId(((Course)this.testRestTemplate.postForObject(this.baseUrl, testCourse, Course.class)).getId());
        });
        this.testRestTemplate.delete(this.baseUrl + "/" + testCourseTwo.getId(), new Object[0]);
        testCourses.remove(testCourseTwo);

        List<Course> remainingCourses = List.of((Course[])this.testRestTemplate.getForObject(this.baseUrl, Course[].class));

        Assertions.assertEquals(testCourses.size(), remainingCourses.size());
        for(int i = 0; i< testCourses.size(); i++){
            assertEquals(testCourses.get(i).getName(), remainingCourses.get(i).getName());
        }
    }
}

