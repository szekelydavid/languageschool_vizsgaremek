package com.codecool.languageschool_szda.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String start_date;
    private String end_date;
    @OneToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
}
