package com.katechcam.schedule_server.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class RootController {
    @GetMapping("/")
    public String home() { return "일정관리 api 시작!!!"; }
}
