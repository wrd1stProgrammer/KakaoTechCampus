package com.katechcam.schedule_server.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Schedule {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
