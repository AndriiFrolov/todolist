package com.pet.todo.list.todolist.unit;

import com.pet.todo.list.todolist.controller.dto.Status;
import com.pet.todo.list.todolist.controller.dto.TaskDto;
import com.pet.todo.list.todolist.core.AbstractTest;
import com.pet.todo.list.todolist.exceptions.ValidationException;
import com.pet.todo.list.todolist.repository.TaskRepository;
import com.pet.todo.list.todolist.repository.entity.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@Tag("unit")
class TaskServiceTest extends AbstractTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(taskRepository);
    }

    @Test
    void whenTaskIsAlreadyCompletedExceptionIsThrown() {
        //Given
        Task task = new Task(1L, "TestSummary", "Test", Status.COMPLETED);

        //When
        Mockito.when(taskRepository.findBySummary(task.getSummary())).thenReturn(task);


        //Then
        Assertions.assertThrows(ValidationException.class, () -> taskService.completeTask(new TaskDto(task)));
    }
}