package com.example.demo.domain;

import com.example.demo.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    private String name;
    private String description;
    private String jumpStart;
    private ToDoDTO toDoDTO;

}
