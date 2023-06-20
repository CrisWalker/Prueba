package com.example.demo.domain;

import com.example.demo.model.ToDo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSpaceDTO {

    private Long id;
    private ToDoDTO toDoDTO;
    private LocalDate date;
    private Integer time;
    private TaskDTO taskDTO;

}
