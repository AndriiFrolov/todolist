package com.pet.todo.list.todolist.controller.dto;

import com.pet.todo.list.todolist.repository.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {
    private String summary;
    private String description;
    private Status status;

    public TaskDto(Task task) {
        this.summary = task.getSummary();
        this.description = task.getDescription();
        this.status = task.getStatus();
    }
}
