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
public class LanguageIntegrationTests {
    @LocalServerPort
    private int port;

    private String baseUrl;


    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port + "/language";
    }

    @Test
    public void addNewLanguage_emptyDatabase_shouldReturnSameLanguage() {
        Teacher testTeacherOne = new Teacher();
        List<Teacher> teachersListTest = new ArrayList<Teacher>();
        teachersListTest.add(testTeacherOne);
        Language testLanguage = new Language((Long)null,teachersListTest,"German");
        testLanguage = testRestTemplate.postForObject(this.baseUrl, testLanguage, Language.class);
        Language result = testRestTemplate.getForObject(this.baseUrl + "/" + testLanguage.getId(), Language.class);
        Assertions.assertEquals(testLanguage.getId(), result.getId());
    }


    @Test
    public void getLanguage_emptyDatabase_returnsEmptyList() {
        List<Language> LanguageList = List.of((Language[])this.testRestTemplate.getForObject(this.baseUrl, Language[].class));
        Assertions.assertEquals(0, LanguageList.size());
    }

    @Test
    public void getLanguageById_withOnePostedLanguagee_returnsLanguageWithSameId() {
        Teacher testTeacherOne = new Teacher();
        List<Teacher> teachersListTest = new ArrayList<Teacher>();
        teachersListTest.add(testTeacherOne);
        Language testLanguage = new Language((Long)null,teachersListTest,"German");
        testLanguage = testRestTemplate.postForObject(this.baseUrl, testLanguage, Language.class);
        Language result = testRestTemplate.getForObject(this.baseUrl + "/" + testLanguage.getId(),  Language.class);
        Assertions.assertEquals(testLanguage.getId(), result.getId());
    }

    @Test
    public void updateLanguage_withOnePostedLanguage_returnsUpdatedLanguage() {
        Teacher testTeacherOne = new Teacher();
        List<Teacher> teachersListTest = new ArrayList<Teacher>();
        teachersListTest.add(testTeacherOne);
        Language testLanguage = new Language((Long)null,teachersListTest,"German");
        testLanguage.setName("Updated name");
        this.testRestTemplate.put(this.baseUrl, testLanguage, new Object[0]);
        Language updatedLanguage = testRestTemplate.getForObject(this.baseUrl + "/" + testLanguage.getId(), Language.class);
        Assertions.assertEquals("Updated name", updatedLanguage.getName());
    }

    @Test
    public void deleteLanguageById_withSomePostedLanguages_getAllShouldReturnRemainingLanguage() {
        Teacher testTeacherOne = new Teacher();
        List<Teacher> teachersListTest = new ArrayList<Teacher>();
        teachersListTest.add(testTeacherOne);
        Language testLanguageOne = new Language((Long)null,teachersListTest,"German");
        Language testLanguageTwo = new Language((Long)null,teachersListTest,"English");
        Language testLanguageThree = new Language((Long)null,teachersListTest,"Spanish");
        List<Language> testLanguages = new ArrayList();
        testLanguages.add(testLanguageOne);
        testLanguages.add(testLanguageTwo);
        testLanguages.add(testLanguageThree);
        testLanguages.forEach((testLanguage) -> {
            testLanguage.setId((this.testRestTemplate.postForObject(this.baseUrl, testLanguage, Language.class)).getId());
        });
        this.testRestTemplate.delete(this.baseUrl + "/" + testLanguageTwo.getId(), new Object[0]);
        testLanguages.remove(testLanguageTwo);
        List<Language> remainingLanguages = List.of(this.testRestTemplate.getForObject(this.baseUrl, Language[].class));
        Assertions.assertEquals(testLanguages.size(), remainingLanguages.size());
        for(int i = 0; i< testLanguages.size(); i++){
            assertEquals(testLanguages.get(i).getName(), remainingLanguages.get(i).getName());
        }
    }

}
