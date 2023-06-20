package com.example.demo.mapper;

import com.example.demo.domain.CategoryDTO;
import com.example.demo.domain.ToDoDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Category;
import com.example.demo.model.ToDo;
import com.example.demo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.OrganizationUtil.fromDateToString;
import static com.example.demo.util.OrganizationUtil.fromStringToDate;

@Component
@RequiredArgsConstructor
public class ToDoMapper {

    private final ToDoRepository toDoRepository;
    private final CategoryMapper categoryMapper;

    public ToDoDTO fromEntityToDTO (ToDo entity){
        ToDoDTO dto = new ToDoDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setJumpStart(entity.getJumpStart());
        dto.setPriority(entity.getPriority());
        //dto.setDeadline(entity.getDeadline() != null ? entity.getDeadline() : null);
        dto.setMaxTime(entity.getMaxTime());
        dto.setCategoryDTO(categoryMapper.fromEntityToDTO(entity.getCategory()));

        return dto;
    }

    public ToDo searchFromDTOtoEntity (ToDoDTO dto)  {
        Optional<ToDo> optionalToDo = toDoRepository.findById(dto.getId());
        if (optionalToDo.isPresent()){
            return optionalToDo.get();
        }
        else{
            throw new CustomException("To do not found", 400);
        }
    }

    public ToDo fromDTOtoEntity (ToDoDTO dto) {
        ToDo entity = new ToDo();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setJumpStart(dto.getJumpStart());
        entity.setPriority(dto.getPriority());
        //entity.setDeadline(dto.getDeadline() != null ? dto.getDeadline() : null);
        entity.setMaxTime(dto.getMaxTime());
        entity.setCategory(categoryMapper.searchFromDTOtoEntity(dto.getCategoryDTO()));

        return entity;
    }

    public List<ToDoDTO> fromEntityListToDTOList (List<ToDo> categories){
        List<ToDoDTO> toDoDTOS = new ArrayList<>();

        for (ToDo toDo : categories) {
            toDoDTOS.add(fromEntityToDTO(toDo));
        }

        return toDoDTOS;

    }
}
