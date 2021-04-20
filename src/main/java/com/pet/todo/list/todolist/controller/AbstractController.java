package com.pet.todo.list.todolist.controller;

import com.pet.todo.list.todolist.exceptions.ExceptionResponse;
import com.pet.todo.list.todolist.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AbstractController {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ExceptionResponse> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage()));
    }

}