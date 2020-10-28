package com.dharbar.repo;

import com.dharbar.repo.enity.Task;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.cloud.gcp.data.datastore.repository.query.Query;

import java.util.List;

public interface TaskRepository extends DatastoreRepository<Task, Long> {

    @Query("SELECT * FROM task WHERE task.isDone = true")
    List<Task> findFinishedTasks();

    List<Task> findAllByTask(String task);
}
