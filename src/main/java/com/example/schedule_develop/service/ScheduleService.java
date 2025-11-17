package com.example.schedule_develop.service;

import com.example.schedule_develop.config.enums.ErrorCode;
import com.example.schedule_develop.config.exception.ScheduleNotFoundException;
import com.example.schedule_develop.config.exception.UserNotFoundException;
import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;
import com.example.schedule_develop.dto.ScheduleRes.*;
import com.example.schedule_develop.dto.common.GlobalResponse;
import com.example.schedule_develop.entity.Schedule;
import com.example.schedule_develop.entity.User;
import com.example.schedule_develop.repository.ScheduleRepository;
import com.example.schedule_develop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.schedule_develop.config.enums.ErrorCode.SCHEDULE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public GlobalResponse<ScheduleResData> create(int status, String message, ScheduleCreateReq req, Long id) {
        //user id 받아서 => userEntity에서 findById() => 거기에 있는 scheduleEntity 에다가 받아온 내용을 집어넣는거죠.
        User user = userRepository.findById(id).orElseThrow(()-> new ScheduleNotFoundException("로그인한 유저만 일정을 생성할 수 있습니다."));
        Schedule schedule = new Schedule(req.getTitle(), req.getContent(), user);
        scheduleRepository.save(schedule);

        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<List<ScheduleResData>> findAll(int status, String message) {

        List<Schedule> list = scheduleRepository.findAll();

        List<ScheduleResData> data = new ArrayList<>();
        for (Schedule schedule : list) {
            ScheduleResData resData = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
            );
                data.add(resData);
        }
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<ScheduleResData> findDetail(int status, String message, Long scheduleID) {
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(() ->
                new ScheduleNotFoundException("일정의 id를 찾을 수 없습니다.")
        );
        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<ScheduleResData> put(int status, String message, Long scheduleID ,Long UserId, SchedulePutReq req) {
        User user = userRepository.findById(UserId).orElseThrow(()-> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getMessage()));
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(() ->
                new ScheduleNotFoundException(SCHEDULE_NOT_FOUND.getMessage())
        );

        if (!schedule.getUser().getId().equals(user.getId())) {
            throw new ScheduleNotFoundException(ErrorCode.USER_NOT_MATCH.getMessage());
        }
        System.out.println(schedule.getUser().getId()+""+user.getId());
        schedule.update(req.getTitle(), req.getContent(), user);
        scheduleRepository.save(schedule);
        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<Void> delete(int status, String message, Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new ScheduleNotFoundException(SCHEDULE_NOT_FOUND.getMessage())
        );
        if (scheduleRepository.existsById(schedule.getScheduleId())) {
            scheduleRepository.deleteById(schedule.getScheduleId());
        }
        return GlobalResponse.successNodata(status, message);
    }

    //로그인 감지 실패시 공통 기능 C U D 에서 공통사용
    public GlobalResponse<Void> sessionFail(int status, String message) {
        return GlobalResponse.fail(status, message);
    }
}
