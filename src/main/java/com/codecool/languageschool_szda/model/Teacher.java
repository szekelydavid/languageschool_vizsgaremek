package com.codecool.languageschool_szda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;
    private String address;
    @ManyToMany
    private List<Language> languages;
}
