package com.pet.todo.list.todolist.repository.entity;


import com.pet.todo.list.todolist.controller.dto.Status;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String summary;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
}
