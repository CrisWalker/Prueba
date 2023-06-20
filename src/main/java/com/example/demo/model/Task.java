package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String jumpStart;

    @JsonBackReference(value="toDo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "toDo_id")
    private ToDo toDo;

}
