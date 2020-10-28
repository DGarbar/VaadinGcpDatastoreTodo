package com.dharbar.controller;

import com.dharbar.repo.enity.Task;
import com.dharbar.service.GcpDatastoreService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final GcpDatastoreService gcpDatastoreService;

    public TaskController(GcpDatastoreService gcpDatastoreService) {
        this.gcpDatastoreService = gcpDatastoreService;
    }

    @GetMapping
    public List<Task> getAll() {
        return gcpDatastoreService.getAllTasks();
    }

    @GetMapping("/finished")
    public List<Task> finishedTasks() {
        return gcpDatastoreService.findFinishedTasks();
    }

    @PostMapping
    public String addTask(@Valid @RequestBody Task task) {
        return gcpDatastoreService.addTask(task.getTask());
    }

    @DeleteMapping
    public void deleteTask(@RequestBody Task task) {
        gcpDatastoreService.deleteTask(task.getTask());
    }
}
