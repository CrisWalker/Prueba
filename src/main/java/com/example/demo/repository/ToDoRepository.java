package com.example.demo.repository;

import com.example.demo.model.TimeSpace;
import com.example.demo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByCategoryId(Long categoryId);

    List<ToDo> findAllByMaxTimeOrderByPriorityDesc(Integer maxTime);

    @Query(value = "SELECT to_do.id, to_do.deadline, to_do.description, to_do.jump_start, to_do.max_time, to_do.name, to_do.priority, to_do.category_id FROM organization.to_do left join organization.category on to_do.category_id=category.id ORDER BY priority desc, category.is_important desc, to_do.id desc", nativeQuery = true)
    List<ToDo> findAllOrdered();

    @Query(value = "SELECT to_do.id, to_do.deadline, to_do.description, to_do.jump_start, to_do.max_time, to_do.name, to_do.priority, to_do.category_id FROM (organization.to_do left join organization.category on to_do.category_id=category.id) left JOIN organization.time_space on to_do.id = time_space.to_do_id WHERE priority > 5 ORDER BY time_space.date asc, priority desc, category.is_important desc, to_do.id desc LIMIT 0,8", nativeQuery = true)
    List<ToDo> findImportantOrdered();

}
