package com.example.schedule_develop.dto.ScheduleRes;

import com.example.schedule_develop.dto.common.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Collection;

@Getter
@AllArgsConstructor
public class ScheduleDelRes extends CommonResponse {

    public ScheduleDelRes(int status, String message) {
        super(status, message);
    }
}
