package com.codecool.languageschool_szda.UnitTests;

import com.codecool.languageschool_szda.controller.LanguageController;
import com.codecool.languageschool_szda.model.Language;
import com.codecool.languageschool_szda.model.Teacher;
import com.codecool.languageschool_szda.service.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LanguageController.class)
public class LanguageUnitTest {
    private static Teacher teacher1;
    private static Teacher teacher2;
    private static Language language1;
    private static Language language2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService service;

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

        language1 = Language.builder()
                .id(1L)
                .name("English")
                .teachers( Arrays.asList(teacher1, teacher2))
                .build();

        language2 = Language.builder()
                .id(2L)
                .name("German")
                .teachers( Arrays.asList(teacher2))
                .build();
    }

    @Test
    public void findAll_shouldReturnListOfCourses() throws Exception {
        when(service.getAll()).thenReturn(List.of(language1, language2));

        mockMvc.perform(get("/language"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
    }

    @Test
    public void findSpellById_shouldReturnLumos() throws Exception {
        when(service.getById(1L)).thenReturn(language1);

        mockMvc.perform(get("/language/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("English")))
                .andExpect(jsonPath("$.teachers", is(Arrays.asList(teacher1, teacher2))));
    }

    @Test
    public void findSpellById_shouldReturnNotFoundStatus() throws Exception {
        when(service.getById(3L)).thenReturn(null);

        mockMvc.perform(get("/course/3"))
                .andExpect(status().isNotFound());
    }
}