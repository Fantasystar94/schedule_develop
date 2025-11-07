package com.example.schedule_develop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedule")
public class ScheduleEntity extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 255)
    private String content;

    public ScheduleEntity(String userName, String title, String content) {
        super();
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }


}
