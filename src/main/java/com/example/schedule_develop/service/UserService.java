package com.example.schedule_develop.service;

import com.example.schedule_develop.config.exception.LoginFailException;
import com.example.schedule_develop.config.passwordEncode.PasswordEncoder;
import com.example.schedule_develop.config.exception.UserNotFoundException;
import com.example.schedule_develop.dto.UserReq.UserCreateReq;
import com.example.schedule_develop.dto.UserReq.UserPutReq;
import com.example.schedule_develop.dto.UserRes.UserDelRes;
import com.example.schedule_develop.dto.UserRes.UserResData;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.ScheduleRepository;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.schedule_develop.config.enums.ErrorCode.LOGIN_FAIL;
import static com.example.schedule_develop.config.enums.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public GlobalResponse<UserResData> create(int status, String message, UserCreateReq req) {
        String encodePassword = passwordEncoder.encode(req.getPassword());
        User user = new User(req.getUserName(),req.getEmail(),encodePassword);
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

    @Transactional(readOnly = true)
    public GlobalResponse<UserResData> findDetail(int status, String message, Long id) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND)); //예외 발생부분.

        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    @Transactional
    public GlobalResponse<UserResData> put(int status, String message, Long id, UserPutReq req) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND));

        user.update(req.getUserName(), req.getEmail());
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new UserNotFoundException(LOGIN_FAIL);
        }
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

    @Transactional
    public GlobalResponse<UserDelRes> delete(int status, String message, Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND));
        if(userRepository.existsById(user.getId())) {
            userRepository.deleteById(user.getId());
        }
        UserDelRes data = new UserDelRes(user.getId(),user.getUserName());
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<UserResData> login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new LoginFailException(LOGIN_FAIL));
        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new LoginFailException(LOGIN_FAIL);
        }

        return new GlobalResponse<>(HttpStatus.OK.value(),"loginSuccessResponse",data);
    }
}
