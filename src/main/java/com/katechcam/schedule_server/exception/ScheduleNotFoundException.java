package com.katechcam.schedule_server.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() { super("일정이 없습니다."); }
}
