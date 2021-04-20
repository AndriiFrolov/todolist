package com.pet.todo.list.todolist.unit;

import com.pet.todo.list.todolist.controller.dto.Status;
import com.pet.todo.list.todolist.controller.dto.TaskDto;
import com.pet.todo.list.todolist.exceptions.ValidationException;
import com.pet.todo.list.todolist.repository.TaskRepository;
import com.pet.todo.list.todolist.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {


    @Autowired
    private TaskRepository taskRepository;

    public TaskDto addTask(TaskDto taskDto) {
        Task task = new Task();
        task.setSummary(taskDto.getSummary());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        taskRepository.save(task);

        return taskDto;
    }

    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public TaskDto deleteTask(TaskDto taskDto) {
        Task bySummary = taskRepository.findBySummary(taskDto.getSummary());
        taskRepository.delete(bySummary);
        return taskDto;
    }

    public TaskDto completeTask(TaskDto taskDto) {
        Task bySummary = taskRepository.findBySummary(taskDto.getSummary());
        if (bySummary.getStatus().equals(Status.COMPLETED)) {
            throw new ValidationException("Task already completed");
        }
        bySummary.setStatus(Status.COMPLETED);
        return new TaskDto(taskRepository.save(bySummary));
    }
}
