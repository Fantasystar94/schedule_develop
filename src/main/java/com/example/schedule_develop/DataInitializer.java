package com.example.schedule_develop;

import com.example.schedule_develop.config.passwordEncode.PasswordEncoder;
import com.example.schedule_develop.entity.Comment;
import com.example.schedule_develop.entity.Schedule;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.CommentRepository;
import com.example.schedule_develop.repository.ScheduleRepository;
import com.example.schedule_develop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        //유저 준비
        User vulture = new User("벌쳐","vulture@gamil.com", passwordEncoder.encode("1234"));
        User marine = new User("마린","marine@gmail.com", passwordEncoder.encode("1234"));
        User dragoon = new User("드라군","dragoon@gmail.com", passwordEncoder.encode("1234"));

        //일정 준비
        Schedule schedule1 = new Schedule("할일","마인심기", vulture);
        Schedule schedule3 = new Schedule("할일","px가서 총사오기", marine);
        Schedule schedule5 = new Schedule("할일","재활훈련하기", dragoon);

        //댓글 준비
        Comment comment1 = new Comment("역대박 조심하세요", schedule1, vulture);
        Comment comment2 = new Comment("내것도 사오세요", schedule3, marine);
        Comment comment3 = new Comment("화이팅!", schedule5, dragoon);

        //유저 생성
        userRepository.save(vulture);
        userRepository.save(marine);
        userRepository.save(dragoon);

        //일정 생성
        scheduleRepository.save(schedule1);
        scheduleRepository.save(schedule3);
        scheduleRepository.save(schedule5);

        //댓글 생성
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
    }
}
