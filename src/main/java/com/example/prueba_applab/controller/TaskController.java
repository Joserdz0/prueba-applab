package com.example.prueba_applab.controller;

import com.example.prueba_applab.entity.Task;
import com.example.prueba_applab.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getById(@PathVariable Long taskId) {
        Optional<Task> taskOpt = taskService.getTask(taskId);
        return taskOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task saved = taskService.saveOrUpdate(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody Task taskDetails) {

        Optional<Task> taskOpt = taskService.getTask(taskId);
        if (taskOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Task taskToUpdate = taskOpt.get();

        taskToUpdate.setDescription(taskDetails.getDescription());
        taskToUpdate.setDate(taskDetails.getDate());
        taskToUpdate.setStatus(taskDetails.getStatus());

        Task updated = taskService.saveOrUpdate(taskToUpdate);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> patchTask(
            @PathVariable Long taskId,
            @RequestBody Task partialTask) {

        Optional<Task> taskOpt = taskService.getTask(taskId);
        if (taskOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Task taskToPatch = taskOpt.get();

        if (partialTask.getStatus() != null) {
            taskToPatch.setStatus(partialTask.getStatus());
        }


        Task patched = taskService.saveOrUpdate(taskToPatch);
        return ResponseEntity.ok(patched);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long taskId) {
        Optional<Task> taskOpt = taskService.getTask(taskId);
        if (taskOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
