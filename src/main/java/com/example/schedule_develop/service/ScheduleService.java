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
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException("없는 아이디"));
        Schedule schedule = new Schedule(req.getUserName(), req.getTitle(), req.getContent(), user);
        scheduleRepository.save(schedule);
        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return new GlobalResponse<>(status, message, data);
    }

    public GlobalResponse<List<ScheduleResData>> findAll(int status, String message, Long id) {

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
        return new GlobalResponse<>(status, message, data);
    }

    public ScheduleReadRes<ScheduleResData> findDetail(int status, String message, Long scheduleID) {

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
        return new ScheduleReadRes<>(status, message, data);
    }

    //put(HttpStatus.OK.value(),"putResponse", scheduleID, req))
    public SchedulePutRes<ScheduleResData> put(int status, String message, Long id ,SchedulePutReq req) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(message)
        );

        schedule.update(req.getTitle(), req.getContent());
        scheduleRepository.save(schedule);
        ScheduleResData data = new ScheduleResData(
            schedule.getScheduleId(),
            schedule.getUserName(),
            schedule.getTitle(),
            schedule.getContent(),
            schedule.getCreatedAt(),
            schedule.getUpdatedAt()
        );
        return new SchedulePutRes<>(status, message, data);
    }

    public ScheduleDelRes delete(int status, String message, Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(message)
        );
        if (scheduleRepository.existsById(schedule.getScheduleId())) {
            scheduleRepository.deleteById(schedule.getScheduleId());
        }
        return new ScheduleDelRes(status, message);
    }

    public GlobalResponse<Void> sessionFail(int status, String message) {

        return GlobalResponse.fail(status, message);
    }
}
