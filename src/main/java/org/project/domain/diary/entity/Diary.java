package org.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String subject;
    private SubjectType subjectType;
    private String content;
    private String tagList;

    private int one = 0;
    private int two = 0;
    private int three = 0;
    private int four = 0;
    private int five = 0;

    public Diary(
            String subject,
            String subjectType,
            String content,
            String tagList
    ) {
        this.subject = subject;
        this.subjectType = SubjectType.of(subjectType);
        this.content = content;
        this.tagList = tagList;
    }
}
