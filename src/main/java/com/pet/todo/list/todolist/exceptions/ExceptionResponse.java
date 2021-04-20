package com.pet.todo.list.todolist.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private Integer responseCode;
    private String details;
}