package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.ScheduleRes.ScheduleResData;
import com.example.schedule_develop.dto.UserReq.UserCreateReq;
import com.example.schedule_develop.dto.UserReq.UserPutReq;
import com.example.schedule_develop.dto.UserRes.UserDelRes;
import com.example.schedule_develop.dto.UserRes.UserResData;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.entity.Schedule;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.ScheduleRepository;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    public GlobalResponse<UserResData> create(int status, String message, UserCreateReq req) {
        User user = new User(req.getUserName(),req.getEmail(),req.getPassword());
        userRepository.save(user);
        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<List<UserResData>> findAll(int status, String message) {

        List<UserResData> userRes = userRepository.findAll().stream().map((user) ->
        new UserResData(
            user.getId(),
            user.getUserName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        )).toList();

        return new GlobalResponse<>(status, message, userRes);
    }

    public GlobalResponse<UserResData> findDetail(int status, String message, Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));

        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<UserResData> put(int status, String message, Long id, UserPutReq req) {

        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));

        user.update(req.getUserName(), req.getEmail());
        System.out.println(req.getUserName());
        userRepository.save(user);
        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<UserDelRes> delete(int status, String message, Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));
        if(userRepository.existsById(user.getId())) {
            userRepository.deleteById(user.getId());
        }
        UserDelRes data = new UserDelRes(user.getId(),user.getUserName());
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<UserResData> login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("이메일 혹은 비밀번호가 일치하지 않습니다."));
        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        if(!user.getPassword().equals(password)) {
            throw new IllegalStateException("이메일 혹은 비밀번호가 일치하지 않습니다.");
        }

        return new GlobalResponse<>(HttpStatus.OK.value(),"loginSuccessResponse",data);
    }
}
