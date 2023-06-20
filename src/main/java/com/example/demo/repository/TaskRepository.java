package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository  extends JpaRepository<Task, Long> {

    List<Task> findAllByToDoId(Long toDoId);

}
