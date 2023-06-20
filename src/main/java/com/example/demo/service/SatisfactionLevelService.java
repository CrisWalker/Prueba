package com.example.demo.service;

import com.example.demo.domain.CategoryDTO;
import com.example.demo.domain.SatisfactionLevelDTO;
import com.example.demo.domain.TaskDTO;
import com.example.demo.mapper.SatisfactionLevelMapper;
import com.example.demo.model.SatisfactionLevel;
import com.example.demo.repository.SatisfactionLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SatisfactionLevelService {

    private final SatisfactionLevelRepository satisfactionLevelRepository;
    private final SatisfactionLevelMapper satisfactionLevelMapper;

    public ResponseEntity<SatisfactionLevelDTO> getSatisfactionLevel(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Optional<SatisfactionLevel> satisfactionLevel = satisfactionLevelRepository.findById(LocalDate.parse(date, formatter));
        if (satisfactionLevel.isPresent()){
            return new ResponseEntity<>(satisfactionLevelMapper.fromEntityToDTO(satisfactionLevel.get()), HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    public ResponseEntity<?> setSatisfactionLevel(SatisfactionLevelDTO satisfactionLevelDTO){
        SatisfactionLevel satisfactionLevel = satisfactionLevelMapper.fromDTOtoEntity(satisfactionLevelDTO);
        try{
            satisfactionLevelRepository.save(satisfactionLevel);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
