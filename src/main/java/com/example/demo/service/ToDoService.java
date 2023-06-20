package com.example.demo.service;

import com.example.demo.domain.CategoryDTO;
import com.example.demo.domain.ToDoDTO;
import com.example.demo.mapper.ToDoMapper;
import com.example.demo.model.Category;
import com.example.demo.model.TimeSpace;
import com.example.demo.model.ToDo;
import com.example.demo.repository.TimeSpaceRepository;
import com.example.demo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoMapper toDoMapper;
    private final TimeSpaceRepository timeSpaceRepository;

    public ResponseEntity<List<ToDoDTO>> findAllToDos(){
        return new ResponseEntity<>(toDoMapper.fromEntityListToDTOList(toDoRepository.findAllOrdered()), HttpStatus.OK);
    }

    public ResponseEntity<List<ToDoDTO>> findImportantOrdered(){
        return new ResponseEntity<>(toDoMapper.fromEntityListToDTOList(toDoRepository.findImportantOrdered()), HttpStatus.OK);
    }

    public ResponseEntity<List<ToDoDTO>> findAllToDosByCategory(Long categoryId){
        return new ResponseEntity<>(toDoMapper.fromEntityListToDTOList(toDoRepository.findAllByCategoryId(categoryId)), HttpStatus.OK);
    }

    public ResponseEntity<?> createToDo (ToDoDTO toDoDTO)  {
        ToDo toDo = toDoMapper.fromDTOtoEntity(toDoDTO);

        toDoRepository.save(toDo);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateToDo (Long id, ToDoDTO toDoDTO)  {
        if (toDoRepository.existsById(id)){
            ToDo toDo = toDoMapper.fromDTOtoEntity(toDoDTO);
            toDo.setId(id);

            toDoRepository.save(toDo);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> deleteToDo (Long id){
        toDoRepository.deleteById(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getRandomToDo(List<String> categoryTypes, Boolean include, Boolean important) {
        // Get all ToDos from the repository
        List<ToDo> filteredToDos = toDoRepository.findAllByMaxTimeOrderByPriorityDesc(0);

        // Filter out ToDos based on categoryIds
        if (categoryTypes != null && !categoryTypes.isEmpty()) {
            filteredToDos = filteredToDos.stream()
                    .filter(todo -> categoryTypes.contains(todo.getCategory().getType()) == include)
                    .collect(Collectors.toList());
        }

        if (important){
            filteredToDos = filteredToDos.stream()
                    .filter(toDo -> toDo.getCategory().getIsImportant())
                    .collect(Collectors.toList());
        }


        // Filter out non-important ToDos and reduce their priority
        filteredToDos = filteredToDos.stream()
                .peek(todo -> {
                    if (!todo.getCategory().getIsImportant()){
                        int reducedPriority = todo.getPriority() / 2; // Reduce the priority by half
                        todo.setPriority(reducedPriority);
                    }
                })
                .collect(Collectors.toList());

        // Select a random ToDo based on weighted probability
        ToDo selectedToDo = null;
        if (!filteredToDos.isEmpty()) {
            int totalWeight = filteredToDos.stream().mapToInt(ToDo::getPriority).sum();
            int randomNumber = new Random().nextInt(totalWeight) + 1; // Generate a random number between 1 and totalWeight
            int cumulativeWeight = 0;
            for (ToDo todo : filteredToDos) {
                cumulativeWeight += todo.getPriority();
                if (randomNumber <= cumulativeWeight) {
                    selectedToDo = todo;
                    if (!selectedToDo.getCategory().getIsImportant()){
                        selectedToDo.setPriority(selectedToDo.getPriority() * 2);
                    }
                    break;
                }
            }
        }

        return new ResponseEntity<>(toDoMapper.fromEntityToDTO(selectedToDo), HttpStatus.OK);
    }

}
