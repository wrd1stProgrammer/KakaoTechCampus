package com.katechcam.schedule_server.exception;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() { super("비밀번호가 일치하지 안흥ㅁ."); }
}
