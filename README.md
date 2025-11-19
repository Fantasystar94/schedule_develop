# ERD
<img width="836" height="339" alt="image" src="https://github.com/user-attachments/assets/a7cdde6c-fd07-4a2b-b225-59f96c0b44c0" />

# API 명세서
https://documenter.getpostman.com/view/49691966/2sB3WwrJAT

# ERD
<img width="836" height="339" alt="image" src="https://github.com/user-attachments/assets/a7cdde6c-fd07-4a2b-b225-59f96c0b44c0" />

# API 명세서
https://documenter.getpostman.com/view/49691966/2sB3WwrJAT

# 기술스택
Spring Boot
JPA / Hibernate
MYSQL
Lombok
Postman
# 구조
Controller -> Serivce -> Repository 3계층 구조로 작업되었습니다.
User -> Schedule -> Comment 엔티티구조로 되어있고,
Schedule은 UserId 를 외래키로, Comment 는 UserId, ScheduleId 를 외래키로 가집니다.
Schedule은 User와 양방향으로, Comment는 Schedule과 양방향 매핑 되어 있습니다.
