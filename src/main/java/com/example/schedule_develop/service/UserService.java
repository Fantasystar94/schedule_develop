package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.UserReq.UserCreateReq;
import com.example.schedule_develop.dto.UserReq.UserPutReq;
import com.example.schedule_develop.dto.UserRes.UserResData;
import com.example.schedule_develop.dto.common.CommonResponse;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.entity.UserEntity;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;

    public GlobalResponse<UserResData> create(int status, String message, UserCreateReq req) {
        UserEntity userEntity = new UserEntity(req.getUserName(),req.getEmail(),req.getPassword());
        userRepo.save(userEntity);
        UserResData data = new UserResData(
                userEntity.getId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<List<UserResData>> findAll(int status, String message) {

        List<UserResData> userRes = userRepo.findAll().stream().map((user) -> new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        )).toList();

        return new GlobalResponse<>(status, message, userRes);
    }

    public GlobalResponse<UserResData> findDetail(int status, String message, Long id) {

        UserEntity user = userRepo.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));

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

        UserEntity user = userRepo.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));

        user.update(req.getUserName(), req.getEmail());
        System.out.println(req.getUserName());
        userRepo.save(user);
        UserResData data = new UserResData(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<UserResData> delete(int status, String message, Long id) {
        UserEntity user = userRepo.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));
        if(userRepo.existsById(user.getId())) {
            userRepo.deleteById(user.getId());
        }

        return new GlobalResponse(status, message, "삭제되었습니다.");
    }
}
