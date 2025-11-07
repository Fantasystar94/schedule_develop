package com.example.schedule_develop.service;

import com.example.schedule_develop.dto.ScheduleReq.ScheduleCreateReq;
import com.example.schedule_develop.dto.ScheduleReq.SchedulePutReq;
import com.example.schedule_develop.dto.ScheduleRes.*;
import com.example.schedule_develop.dto.common.CommonResponse;
import com.example.schedule_develop.entity.ScheduleEntity;
import com.example.schedule_develop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepo;

    public ScheduleCreateRes create(int status, String message, ScheduleCreateReq req) {
        ScheduleEntity schedule = new ScheduleEntity(req.getUserName(), req.getTitle(), req.getContent());
        scheduleRepo.save(schedule);
        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        ScheduleCreateRes scheduleCreateRes = new ScheduleCreateRes(status, message, data);
        return scheduleCreateRes;
    }

    public ScheduleReadRes findAll(int status, String message) {

        List<ScheduleEntity> list = scheduleRepo.findAll();
        List<ScheduleResData> data = new ArrayList<>();
        for (ScheduleEntity schedule : list) {
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
        ScheduleReadRes scheduleReadRes = new ScheduleReadRes(status, message, data);
        return scheduleReadRes;
    }

    public ScheduleReadRes findDetail(int status, String message, Long scheduleID) {

        ScheduleEntity schedule = scheduleRepo.findById(scheduleID).orElseThrow(() ->
                new IllegalStateException(message)
        );
        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        ScheduleReadRes scheduleReadRes = new ScheduleReadRes(status, message, data);
        return scheduleReadRes;
    }

    //put(HttpStatus.OK.value(),"putResponse", scheduleID, req))
    public SchedulePutRes put(int status, String message, Long id ,SchedulePutReq req) {

        ScheduleEntity schedule = scheduleRepo.findById(id).orElseThrow(() ->
                new IllegalStateException(message)
        );

        schedule.update(req.getTitle(), req.getContent());
        scheduleRepo.save(schedule);
        ScheduleResData data = new ScheduleResData(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
        return new SchedulePutRes(status, message, data);
    }

    public ScheduleDelRes delete(int status, String message, Long id) {
        ScheduleEntity schedule = scheduleRepo.findById(id).orElseThrow(() ->
                new IllegalStateException(message)
        );
        if (scheduleRepo.existsById(schedule.getScheduleId())) {
            scheduleRepo.deleteById(schedule.getScheduleId());
        }
        return new ScheduleDelRes(status, message);
    }
}
