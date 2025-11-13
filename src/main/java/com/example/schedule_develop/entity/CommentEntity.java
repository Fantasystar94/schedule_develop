package com.example.schedule_develop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class CommentEntity extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id",nullable = false)
    private Schedule schedule;

    public CommentEntity(String content) {
        super();
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }

}
