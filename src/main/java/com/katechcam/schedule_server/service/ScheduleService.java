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

    /* ───────── CRUD ───────── */

    /** 일정 등록 */
    public ScheduleResponse create(ScheduleRequest req) {
        Schedule s = new Schedule(
                null,
                req.title(),
                req.content(),
                req.writer(),
                req.password(),            // ⬅️ 해시 대신 평문 그대로 저장
                req.startAt(),
                req.endAt(),
                null,
                null);
        Long id = repo.save(s);
        return toDto(repo.findById(id).orElseThrow());
    }

    /** 단건 조회 */
    public ScheduleResponse get(Long id) {
        return toDto(repo.findById(id).orElseThrow(ScheduleNotFoundException::new));
    }

    /** 전체 조회 */
    public List<ScheduleResponse> getAll() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    /** 일정 수정 */
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

    /** 일정 삭제 */
    public void delete(Long id, String password) {
        Schedule s = repo.findById(id).orElseThrow(ScheduleNotFoundException::new);
        if (!s.getPassword().equals(password))
            throw new PasswordMismatchException();
        repo.delete(id);
    }

    /* Entity → DTO 변환 */
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
