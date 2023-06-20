package com.example.demo.mapper;

import com.example.demo.domain.SatisfactionLevelDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.model.SatisfactionLevel;
import com.example.demo.repository.SatisfactionLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SatisfactionLevelMapper {

    private final SatisfactionLevelRepository categoryRepository;

    public SatisfactionLevelDTO fromEntityToDTO (SatisfactionLevel entity){
        SatisfactionLevelDTO dto = new SatisfactionLevelDTO();

        dto.setDate(entity.getDate());
        dto.setLevel(entity.getLevel());

        return dto;
    }

    public SatisfactionLevel fromDTOtoEntity (SatisfactionLevelDTO dto) {
        SatisfactionLevel entity = new SatisfactionLevel();

        entity.setDate(dto.getDate());
        entity.setLevel(dto.getLevel());

        return entity;
    }
}
