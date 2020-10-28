package com.dharbar.repo.enity;

import lombok.Value;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Value(staticConstructor = "of")
public class Task {

    @Id
    Long id;

    @NotNull(message = "task can't be null")
    @NotBlank(message = "task can't be blank")
    String task;

    Boolean isDone;
}
