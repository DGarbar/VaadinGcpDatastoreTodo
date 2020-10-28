package com.dharbar.repo;

import com.dharbar.repo.enity.Task;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface TaskRepository extends DatastoreRepository<Task, String> {
}
