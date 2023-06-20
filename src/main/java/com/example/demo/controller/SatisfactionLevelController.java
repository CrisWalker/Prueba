package com.example.demo.controller;

import com.example.demo.domain.SatisfactionLevelDTO;
import com.example.demo.model.SatisfactionLevel;
import com.example.demo.service.SatisfactionLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/satisfactionLevel")
@CrossOrigin(origins = "http://localhost:4200/")
public class SatisfactionLevelController {

    private final SatisfactionLevelService satisfactionLevelService;


    @GetMapping("/{date}")
    public ResponseEntity<?> getSatisfactionLevel(@PathVariable String date){
        return satisfactionLevelService.getSatisfactionLevel(date);
    }

    @PostMapping()
    public ResponseEntity<?> getCategories(@RequestBody SatisfactionLevelDTO satisfactionLevelDTO){
        return satisfactionLevelService.setSatisfactionLevel(satisfactionLevelDTO);
    }

}
