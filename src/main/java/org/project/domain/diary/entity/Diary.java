package org.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.*;
import org.project.domain.user.entity.User;
import org.project.global.entity.BaseEntity;

import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "diary")
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = true)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectType subjectType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String tagList;

    private int one = 0;
    private int two = 0;
    private int three = 0;
    private int four = 0;
    private int five = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Diary(
            String subject,
            String subjectType,
            String title,
            String content,
            String tagList
    ) {
        this.subject = subject;
        this.subjectType = SubjectType.of(subjectType);
        this.title = title;
        this.content = content;
        this.tagList = tagList;
    }
}
