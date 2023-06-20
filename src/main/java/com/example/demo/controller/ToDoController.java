package com.example.demo.controller;

import com.example.demo.domain.ToDoDTO;
import com.example.demo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/toDo")
@CrossOrigin(origins = "http://localhost:4200/")
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public ResponseEntity<?> getToDos(){
        return toDoService.findAllToDos();
    }

    @GetMapping("/important")
    public ResponseEntity<?> findImportantOrdered(){
        return toDoService.findImportantOrdered();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getToDosByCategory(@PathVariable Long categoryId){
        return toDoService.findAllToDosByCategory(categoryId);
    }

    @PostMapping
    public ResponseEntity<?> createToDo(@RequestBody ToDoDTO toDoDTO)  {
        return toDoService.createToDo(toDoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateToDo(@PathVariable Long id, @RequestBody ToDoDTO toDoDTO)  {
        return toDoService.updateToDo(id, toDoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable Long id){
        return toDoService.deleteToDo(id);
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomToDo(@RequestParam(value = "categoryIds", required = false) List<String> categoryTypes
            , @RequestParam(value = "include", required = false) Boolean include
            , @RequestParam(value = "important", required = false) Boolean important) {
        return toDoService.getRandomToDo(categoryTypes, include, important);
    }
}
