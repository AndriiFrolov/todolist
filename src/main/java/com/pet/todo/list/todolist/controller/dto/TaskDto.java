package com.pet.todo.list.todolist.controller.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String summary;
    private String description;
    private Status status;
}
