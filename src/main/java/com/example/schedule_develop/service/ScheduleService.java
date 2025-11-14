package com.example.schedule_develop.service;

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

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public GlobalResponse<ScheduleResData> create(int status, String message, ScheduleCreateReq req, Long id) {
        //user id 받아서 => userEntity에서 findById() => 거기에 있는 scheduleEntity 에다가 받아온 내용을 집어넣는거죠.
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException(message));
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
                new IllegalStateException("id를 찾을 수 없습니다.")
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

    public GlobalResponse<ScheduleResData> put(int status, String message, Long scheduleID ,Long UserId,SchedulePutReq req) {
        User user = userRepository.findById(UserId).orElseThrow(()-> new IllegalStateException(message));
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(() ->
                new IllegalStateException(message)
        );

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
                new IllegalStateException(message)
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
