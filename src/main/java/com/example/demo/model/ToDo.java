package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String jumpStart;

    private Integer priority;

    private LocalDate deadline;

    private Integer maxTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Category category;

    @OneToMany(mappedBy = "toDo", cascade = CascadeType.REMOVE)
    private List<Task> tasks;

}
