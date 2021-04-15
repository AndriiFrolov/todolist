package com.pet.todo.list.todolist.repository;

import com.pet.todo.list.todolist.repository.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Task findBySummary(String summary);
}
