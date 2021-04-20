package com.pet.todo.list.todolist.controller;

import com.pet.todo.list.todolist.controller.dto.Status;
import com.pet.todo.list.todolist.controller.dto.TaskDto;
import com.pet.todo.list.todolist.repository.entity.Task;
import com.pet.todo.list.todolist.unit.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController extends AbstractController{

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto addTask(@RequestBody TaskDto taskDto) {
        taskDto.setStatus(Status.NOT_STARTED);
        return taskService.addTask(taskDto);
    }

    @PatchMapping
    public TaskDto completeTask(@RequestBody TaskDto taskDto) {
        taskDto.setStatus(Status.COMPLETED);
        return taskService.completeTask(taskDto);
    }


    @DeleteMapping
    public TaskDto deleteTask(@RequestBody TaskDto taskDto) {
        return taskService.deleteTask(taskDto);
    }


    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

}
