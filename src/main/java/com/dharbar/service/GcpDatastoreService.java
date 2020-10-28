package com.dharbar.service;

import com.dharbar.repo.TaskRepository;
import com.dharbar.repo.enity.Task;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GcpDatastoreService implements Serializable {

    private final TaskRepository taskRepository;

    public GcpDatastoreService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public String addTask(String task) {
        final Task savedTask = taskRepository.save(Task.of(Instant.now().toEpochMilli(), task));
        return savedTask.getTask();
    }

    public List<String> getAllTasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .map(Task::getTask)
                .collect(Collectors.toList());
    }
}
