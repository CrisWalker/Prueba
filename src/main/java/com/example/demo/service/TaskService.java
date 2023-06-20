package com.example.demo.service;

import com.example.demo.domain.TaskDTO;
import com.example.demo.domain.TaskDTO;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public ResponseEntity<List<TaskDTO>> findAllTasks(){
        return new ResponseEntity<>(taskMapper.fromEntityListToDTOList(taskRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<List<TaskDTO>> findAllTasksByToDo(Long toDoId){
        return new ResponseEntity<>(taskMapper.fromEntityListToDTOList(taskRepository.findAllByToDoId(toDoId)), HttpStatus.OK);
    }
    
    public ResponseEntity<?> createTask (TaskDTO taskDTO){
        Task task = taskMapper.fromDTOtoEntity(taskDTO);

        taskRepository.save(task);

        return new ResponseEntity<>(taskMapper.fromEntityToDTO(task), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteTask (Long id){
        taskRepository.deleteById(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateTask (Long id, TaskDTO taskDTO){
        if (taskRepository.existsById(id)){
            Task task = taskMapper.fromDTOtoEntity(taskDTO);
            task.setId(id);

            taskRepository.save(task);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

}
