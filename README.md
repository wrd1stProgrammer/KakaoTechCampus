## ERD
![ktcERD](https://github.com/user-attachments/assets/339d5b42-73cc-4ac0-8ba9-d5019950996e)



## API 명세서

## 1. 일정 등록 `POST /api/schedule`

| **Request-Body** | ```json { "title": "회의", "content": "킥오프", "writer": "Alice", "password": "qwer1234", "startAt": "2025-06-01T10:00:00", "endAt": "2025-06-01T11:00:00" }``` |
| **성공** | `201 Created` &nbsp;↦ `ScheduleResponse` |
| **실패** | `400 Bad Request` – 필수 필드 누락 |

---

## 2. 일정 단건 조회 `GET /api/schedule/{id}`

| 구분 | 내용 |
|------|------|
| **Path Param** | `id` : 일정 ID |
| **성공** | `200 OK` + `ScheduleResponse` |
| **실패** | `404 Not Found` – 존재하지 않는 ID |

---

## 3. 전체 목록 조회 `GET /api/schedule`

| 구분 | 내용 |
|------|------|
| **Query** | 없음 |
| **성공** | `200 OK` + `ScheduleResponse[]` |

---

## 4. 일정 수정 `PUT /api/schedule/{id}`

| 구분 | 내용 |
|------|------|
| **Path Param** | `id` : 일정 ID |
| **Request-Body** | 등록ㄱ과 동일 필드 + 기존 `password` |
| **성공** | `200 OK` + 수정된 `ScheduleResponse` |
| **실패** | `401 Unauthorized` – 비밀번호 불일치<br>`404 Not Found` – ID 없음 |

---

## 5. 일정 삭제 `DELETE /api/schedule/{id}?password={pw}`

| 구분 | 내용 |
|------|------|
| **Path Param** | `id` : 삭제할 일정 ID |
| **Query** | `password` : 기존 비밀번호 |
| **성공** | `204 No Content` |
| **실패** | `401 Unauthorized` – 비밀번호 불일치<br>`404 Not Found` – ID 없음 |

---

## 6. 객체 스키마

### 6-1 `ScheduleResponse`

```jsonc
{
  "id": 1,
  "title": "카테캠 화의",
  "content": "스프링에 관하여",
  "writer": "채민식",
  "startAt": "2025-06-01T10:00:00",
  "endAt": "2025-06-01T11:00:00",
  "createdAt": "2025-05-25T14:12:34",
  "updatedAt": "2025-05-25T14:12:34"
}

