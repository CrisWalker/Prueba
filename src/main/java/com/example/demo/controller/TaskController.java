package com.example.demo.controller;

import com.example.demo.domain.TaskDTO;
import com.example.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200/")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTasks(){
        return taskService.findAllTasks();
    }

    @GetMapping("/{toDoId}")
    public ResponseEntity<?> getTasksByToDo(@PathVariable Long toDoId){
        return taskService.findAllTasksByToDo(toDoId);
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO){
        return taskService.createTask(taskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return taskService.deleteTask(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO){
        return taskService.updateTask(id, taskDTO);
    }

}
