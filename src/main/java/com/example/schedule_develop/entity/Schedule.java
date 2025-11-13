package com.example.schedule_develop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 255)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    public Schedule(String userName, String title, String content, User user) {
        super();
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }

}
