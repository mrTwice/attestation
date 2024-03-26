package ru.yampolskiy.taskclient.models.task;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonSerialize
@JsonDeserialize
public class Task {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long ownerId;
    private LocalDateTime created;
    private LocalDateTime lastUpdate;
    private LocalDateTime finished;

    public Task(String title, String description, TaskStatus status, Long ownerId, LocalDateTime created, LocalDateTime lastUpdate, LocalDateTime finished) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.ownerId = ownerId;
        this.created = created;
        this.lastUpdate = lastUpdate;
        this.finished = finished;
    }
}
