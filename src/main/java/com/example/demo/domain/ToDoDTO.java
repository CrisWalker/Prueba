package com.example.demo.domain;

import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoDTO {

    private Long id;

    private String name;

    private String description;

    private String jumpStart;

    private Integer priority;

    //private LocalDate deadline;

    private Integer maxTime;

    private CategoryDTO categoryDTO;

}
