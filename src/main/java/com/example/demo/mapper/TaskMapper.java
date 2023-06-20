package com.example.demo.mapper;

import com.example.demo.domain.TaskDTO;
import com.example.demo.domain.ToDoDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Task;
import com.example.demo.model.ToDo;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final ToDoMapper toDoMapper;
    private final TaskRepository taskRepository;

    public TaskDTO fromEntityToDTO (Task entity){
        TaskDTO dto = new TaskDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setJumpStart(entity.getJumpStart());
        dto.setToDoDTO(toDoMapper.fromEntityToDTO(entity.getToDo()));

        return dto;
    }

    public Task fromDTOtoEntity (TaskDTO dto)  {
        Task entity = new Task();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setJumpStart(dto.getJumpStart());
        entity.setToDo(toDoMapper.searchFromDTOtoEntity(dto.getToDoDTO()));

        return entity;
    }

    public List<TaskDTO> fromEntityListToDTOList (List<Task> categories){
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task task : categories) {
            taskDTOS.add(fromEntityToDTO(task));
        }

        return taskDTOS;

    }

    public Task searchFromDTOtoEntity (TaskDTO dto)  {
        Optional<Task> optionalTask = taskRepository.findById(dto.getId());
        if (optionalTask.isPresent()){
            return optionalTask.get();
        }
        else{
            throw new CustomException("Task not found", 400);
        }
    }
}
