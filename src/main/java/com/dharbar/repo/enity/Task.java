package com.dharbar.repo.enity;

import lombok.Value;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity
@Value(staticConstructor = "of")
public class Task {

    @Id
    Long id;
    String task;

}
