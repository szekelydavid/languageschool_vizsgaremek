package com.codecool.languageschool_szda.service;

import com.codecool.languageschool_szda.model.Course;
import com.codecool.languageschool_szda.model.Language;
import com.codecool.languageschool_szda.repository.CourseRepository;
import com.codecool.languageschool_szda.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LanguageService {
    private LanguageRepository languageRepository;

    @Autowired
    private TeacherService teacherService;


    public Language getById(Long id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) return optionalLanguage.get();
        else throw new RuntimeException("Course does not exist");
    }

    public List<Language> getAll() {
        return languageRepository.findAll();
    }


    public Language add(Language language) {
        return languageRepository.save(language);
    }

    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
