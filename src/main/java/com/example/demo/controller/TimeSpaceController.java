package com.example.demo.controller;

import com.example.demo.domain.TimeSpaceDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.service.TimeSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeSpace")
@CrossOrigin(origins = "http://localhost:4200/")
public class TimeSpaceController {

    private final TimeSpaceService timeSpaceService;

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getTimeSpacesByDate(@PathVariable String date)  {
        return timeSpaceService.findAllTimeSpacesByDate(date);
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getTimeByDate(@PathVariable String date)  {
        return timeSpaceService.findTimeByDate(date);
    }

    @GetMapping("/toDo/{toDoId}")
    public ResponseEntity<?> getTimeSpacesByToDo(@PathVariable Long toDoId)  {
        return timeSpaceService.findAllTimeSpacesByToDo(toDoId);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getTimeSpacesByTask(@PathVariable Long taskId)  {
        return timeSpaceService.findAllTimeSpacesByTask(taskId);
    }

    @GetMapping("/last")
    public ResponseEntity<?> getLastTimeSpace(){
        return timeSpaceService.getLastTimeSpace();
    }

    @GetMapping
    public ResponseEntity<?> getTimeSpaces()  {
        return timeSpaceService.findAllTimeSpaces();
    }

    @PostMapping
    public ResponseEntity<?> createTimeSpace(@RequestBody TimeSpaceDTO timeSpaceDTO)  {
        return timeSpaceService.createTimeSpace(timeSpaceDTO);
    }

    @PutMapping("/{timeSpaceId}")
    public ResponseEntity<?> updateTimeSpace(@PathVariable Long timeSpaceId, @RequestBody TimeSpaceDTO timeSpaceDTO)  {
        return timeSpaceService.updateTimeSpace(timeSpaceId, timeSpaceDTO);
    }

    @DeleteMapping("/{timeSpaceId}")
    public ResponseEntity<?> updateTimeSpace(@PathVariable Long timeSpaceId) {
        return timeSpaceService.deleteTimeSpace(timeSpaceId);
    }

}
