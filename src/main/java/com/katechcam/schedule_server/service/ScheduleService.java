package com.katechcam.schedule_server.service;

import com.katechcam.schedule_server.domain.Schedule;
import com.katechcam.schedule_server.dto.*;
import com.katechcam.schedule_server.exception.*;
import com.katechcam.schedule_server.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repo;

    public ScheduleResponse create(ScheduleRequest req) {
        Schedule s = new Schedule(
                null,
                req.title(),
                req.content(),
                req.writer(),
                req.password(),
                req.startAt(),
                req.endAt(),
                null,
                null);
        Long id = repo.save(s);
        return toDto(repo.findById(id).orElseThrow());
    }

    public ScheduleResponse get(Long id) {
        return toDto(repo.findById(id).orElseThrow(ScheduleNotFoundException::new));
    }

    public List<ScheduleResponse> getAll() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public ScheduleResponse update(Long id, ScheduleRequest req) {
        Schedule s = repo.findById(id).orElseThrow(ScheduleNotFoundException::new);
        if (!s.getPassword().equals(req.password()))   // 평문 비교
            throw new PasswordMismatchException();

        s.setTitle(req.title());
        s.setContent(req.content());
        s.setStartAt(req.startAt());
        s.setEndAt(req.endAt());
        repo.update(s);
        return toDto(repo.findById(id).orElseThrow());
    }

    public void delete(Long id, String password) {
        Schedule s = repo.findById(id).orElseThrow(ScheduleNotFoundException::new);
        if (!s.getPassword().equals(password))
            throw new PasswordMismatchException();
        repo.delete(id);
    }
    //dto
    private ScheduleResponse toDto(Schedule s) {
        return new ScheduleResponse(
                s.getId(),
                s.getTitle(),
                s.getContent(),
                s.getWriter(),
                s.getStartAt(),
                s.getEndAt(),
                s.getCreatedAt(),
                s.getUpdatedAt());
    }
}
