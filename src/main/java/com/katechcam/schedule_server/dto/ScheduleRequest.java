package com.katechcam.schedule_server.dto;

import java.time.LocalDateTime;

public record ScheduleRequest(
        String title,
        String content,
        String writer,
        String password,
        LocalDateTime startAt,
        LocalDateTime endAt
) {}
