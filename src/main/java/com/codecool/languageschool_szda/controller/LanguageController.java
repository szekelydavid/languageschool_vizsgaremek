package com.codecool.languageschool_szda.controller;

import com.codecool.languageschool_szda.model.Language;
import com.codecool.languageschool_szda.service.LanguageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {
    private LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<Language> getAll() {
        return languageService.getAll();
    }

    @GetMapping("/{id}")
    public Language getById(@PathVariable("id") Long id){
        return languageService.getById(id);
    }

    @PostMapping
    public Language add(@RequestBody Language language) {
        return languageService.add(language);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        languageService.deleteById(id);
    }



}
