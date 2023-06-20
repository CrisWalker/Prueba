package com.example.demo.repository;

import com.example.demo.model.TimeSpace;
import com.example.demo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TimeSpaceRepository extends JpaRepository<TimeSpace, Long> {

    List<TimeSpace> findAllByDate(LocalDate date);
    List<TimeSpace> findAllByToDoId(Long toDoId);
    List<TimeSpace> findAllByTaskId(Long toDoId);
    Optional<TimeSpace> findByToDoAndDate (ToDo toDo, LocalDate date);

    @Query(value = "SELECT * FROM organization.time_space ORDER BY date desc, id desc LIMIT 1", nativeQuery = true)
    TimeSpace findTopByOrderByDateAndIdDesc();

    @Query(value = "SELECT * FROM organization.time_space ORDER BY date desc, id desc", nativeQuery = true)
    List<TimeSpace> findByOrderByDateAndIdDesc();

}
