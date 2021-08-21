package com.codecool.languageschool_szda.integration;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Language;
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
public class TeacherIntegrationTests {
    @LocalServerPort
    private int port;

    private String baseUrl;


    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/teacher";
    }

    @Test
    public void addNeweacher_emptyDatabase_shouldReturnSameTeacher() {

        Language testLanguage = new Language();
        List<Language> testLanguageList = new ArrayList<Language>();
        testLanguageList.add(testLanguage);
        Teacher testTeacher = new Teacher((Long)null,"Joseph Karl",45,"Budapest",testLanguageList);
        testTeacher = testRestTemplate.postForObject(this.baseUrl, testTeacher, Teacher.class);
        Teacher result = testRestTemplate.getForObject(this.baseUrl + "/" + testTeacher.getId(), Teacher.class);
        Assertions.assertEquals(testTeacher.getId(), result.getId());
    }


    @Test
    public void getTeacher_emptyDatabase_returnsEmptyList() {
        List<Teacher> teacherList = List.of((Teacher[])this.testRestTemplate.getForObject(this.baseUrl, Teacher[].class));
        Assertions.assertEquals(0, teacherList.size());
    }

    @Test
    public void getTeacherById_withOnePostedCourse_returnsCourseWithSameId() {
        Language testLanguage = new Language();
        List<Language> testLanguageList = new ArrayList<Language>();
        testLanguageList.add(testLanguage);
        Teacher testTeacher = new Teacher((Long)null,"Joseph Karl",45,"Budapest",testLanguageList);
        Teacher result = testRestTemplate.getForObject(this.baseUrl + "/" + testTeacher.getId(), Teacher.class);
        Assertions.assertEquals(testTeacher.getId(), result.getId());
    }

    @Test
    public void updateCourse_withOnePostedCourse_returnsUpdatedCourse() {
        Language testLanguage = new Language();
        List<Language> testLanguageList = new ArrayList<Language>();
        testLanguageList.add(testLanguage);
        Teacher testTeacher = new Teacher((Long)null,"Joseph Karl",45,"Budapest",testLanguageList);
        testTeacher = testRestTemplate.postForObject(this.baseUrl, testTeacher, Teacher.class, new Object[0]);
        testTeacher.setName("Updated name");
        this.testRestTemplate.put(this.baseUrl, testTeacher, new Object[0]);
        Course updatedCourse = testRestTemplate.getForObject(this.baseUrl + "/" + testTeacher.getId(), Course.class);
        Assertions.assertEquals("Updated name", updatedCourse.getName());
    }

    @Test
    public void deleteTeacherById_withSomePostedTeachers_getAllShouldReturnRemainingTeacher() {
        Language testLanguage = new Language();
        List<Language> testLanguageList = new ArrayList<Language>();
        testLanguageList.add(testLanguage);
        Teacher testTeacherOne = new Teacher((Long)null,"Joseph Karl",45,"Budapest",testLanguageList);
        Teacher testTeacherTwo = new Teacher((Long)null,"Fantomas",29,"Belgium",testLanguageList);
        Teacher testTeacherThree = new Teacher((Long)null,"Karl May",72,"Tokio",testLanguageList);
        List<Teacher> testTeachers = new ArrayList();
        testTeachers.add(testTeacherOne);
        testTeachers.add(testTeacherTwo);
        testTeachers.add(testTeacherThree);
        testTeachers.forEach((testTeacher) -> {
            testTeacher.setId(((Teacher)this.testRestTemplate.postForObject(this.baseUrl, testTeacher, Teacher.class)).getId());
        });
        this.testRestTemplate.delete(this.baseUrl + "/" + testTeacherTwo.getId(), new Object[0]);
        testTeachers.remove(testTeacherTwo);

        List<Teacher> remainingTeachers = List.of((Teacher[])this.testRestTemplate.getForObject(this.baseUrl, Teacher[].class));

        Assertions.assertEquals(testTeachers.size(), remainingTeachers.size());
        for(int i = 0; i< testTeachers.size(); i++){
            assertEquals(testTeachers.get(i).getName(), remainingTeachers.get(i).getName());
        }
    }

}
