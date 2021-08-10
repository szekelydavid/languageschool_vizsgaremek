package com.codecool.languageschool_szda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Language {
    @Id
    @GeneratedValue
    private Long id;
    @JsonIgnoreProperties("languages")
    @ManyToMany
    private List<Teacher> teachers;
    private String name;
}
