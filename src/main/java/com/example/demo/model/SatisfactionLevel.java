package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SatisfactionLevel {

    @Id
    private LocalDate date;

    private Long level;

    public SatisfactionLevel(Long level) {
        this.date = LocalDate.now();
        this.level = level;
    }
}
