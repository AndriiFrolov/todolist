package com.pet.todo.list.todolist.integration;

import com.pet.todo.list.todolist.controller.dto.Status;
import com.pet.todo.list.todolist.controller.dto.TaskDto;
import com.pet.todo.list.todolist.core.AbstractTest;
import com.pet.todo.list.todolist.repository.TaskRepository;
import com.pet.todo.list.todolist.repository.entity.Task;
import com.pet.todo.list.todolist.unit.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Tag("integration")
class TaskServiceITTest extends AbstractTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void whenTaskIsCompletedStatusSHouldBeChanged() {
        //Given
        Task task = new Task(1L, "Test", "TestDescription", Status.NOT_STARTED);
        taskRepository.save(task);

        //When
        taskService.completeTask(new TaskDto(task));

        //Then
        Optional<Task> updatedTask = taskRepository.findById(task.getId());
        Assertions.assertTrue(updatedTask.isPresent());
        Assertions.assertEquals(Status.COMPLETED, updatedTask.get().getStatus());
    }
}