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

    public List<Task> getAllTasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Task> findFinishedTasks() {
        return taskRepository.findFinishedTasks();
    }

    public String addTask(String task) {
        final Task savedTask = taskRepository.save(Task.of(Instant.now().toEpochMilli(), task, false));
        return savedTask.getTask();
    }

    public void deleteTask(String taskName) {
        taskRepository.findAllByTask(taskName).forEach(taskRepository::delete);
    }
}
