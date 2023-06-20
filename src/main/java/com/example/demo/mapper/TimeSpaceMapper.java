package com.example.demo.mapper;

import com.example.demo.domain.TimeSpaceDTO;
import com.example.demo.domain.ToDoDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.model.TimeSpace;
import com.example.demo.model.ToDo;
import com.example.demo.repository.TimeSpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.OrganizationUtil.fromDateToString;
import static com.example.demo.util.OrganizationUtil.fromStringToDate;

@Component
@RequiredArgsConstructor
public class TimeSpaceMapper {

    private final ToDoMapper toDoMapper;
    private final TaskMapper taskMapper;

    public TimeSpaceDTO fromEntityToDTO (TimeSpace entity){
        TimeSpaceDTO dto = new TimeSpaceDTO();

        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setToDoDTO(toDoMapper.fromEntityToDTO(entity.getToDo()));
        dto.setTaskDTO(entity.getTask() != null ? taskMapper.fromEntityToDTO(entity.getTask()): null);

        return dto;
    }

    public TimeSpace fromDTOtoEntity (TimeSpaceDTO dto)  {
        TimeSpace entity = new TimeSpace();

        if (dto.getDate() == null){
            dto.setDate(LocalDate.now());
        }
        entity.setDate(dto.getDate());
        entity.setTime(dto.getTime());
        entity.setToDo(toDoMapper.searchFromDTOtoEntity(dto.getToDoDTO()));
        entity.setTask(dto.getTaskDTO() != null && dto.getTaskDTO().getId() != null ? taskMapper.searchFromDTOtoEntity(dto.getTaskDTO()) : null);

        return entity;
    }

    public List<TimeSpaceDTO> fromEntityListToDTOList (List<TimeSpace> categories){
        List<TimeSpaceDTO> timeSpaceDTOS = new ArrayList<>();

        for (TimeSpace timeSpace : categories) {
            timeSpaceDTOS.add(fromEntityToDTO(timeSpace));
        }

        return timeSpaceDTOS;

    }
}
