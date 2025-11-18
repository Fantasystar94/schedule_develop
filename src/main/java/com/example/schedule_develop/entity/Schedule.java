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
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // optioanl = false => null일수 없다.
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(String title, String content, User user) {
        super();
        this.userName = user.getUserName();
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update (String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

}
