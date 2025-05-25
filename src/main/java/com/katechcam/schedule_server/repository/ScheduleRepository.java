package com.katechcam.schedule_server.repository;

import com.katechcam.schedule_server.domain.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleRepository {

    private final JdbcTemplate jdbc;

    private final RowMapper<Schedule> mapper = (rs, n) -> {
        Schedule s = new Schedule();
        s.setId(rs.getLong("id"));
        s.setTitle(rs.getString("title"));
        s.setContent(rs.getString("content"));
        s.setWriter(rs.getString("writer"));
        s.setPassword(rs.getString("password"));
        s.setStartAt(rs.getTimestamp("start_at").toLocalDateTime());
        s.setEndAt(rs.getTimestamp("end_at").toLocalDateTime());
        s.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        s.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return s;
    };

    public Long save(Schedule s) {
        String sql = """
            INSERT INTO schedules(title,content,writer,password,start_at,end_at)
            VALUES(?,?,?,?,?,?)
        """;
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps =
                    con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s.getTitle());
            ps.setString(2, s.getContent());
            ps.setString(3, s.getWriter());
            ps.setString(4, s.getPassword());
            ps.setTimestamp(5, Timestamp.valueOf(s.getStartAt()));
            ps.setTimestamp(6, Timestamp.valueOf(s.getEndAt()));
            return ps;
        }, kh);
        return kh.getKey().longValue();
    }

    public Optional<Schedule> findById(Long id) {
        return jdbc.query(
                "SELECT * FROM schedules WHERE id=?", mapper, id).stream().findFirst();
    }

    public List<Schedule> findAll() {
        return jdbc.query("SELECT * FROM schedules ORDER BY created_at DESC", mapper);
    }

    public int update(Schedule s) {
        return jdbc.update("""
            UPDATE schedules SET title=?,content=?,start_at=?,end_at=?,updated_at=NOW()
            WHERE id=?
        """, s.getTitle(), s.getContent(),
                s.getStartAt(), s.getEndAt(), s.getId());
    }

    public int delete(Long id) {
        return jdbc.update("DELETE FROM schedules WHERE id=?", id);
    }
}
