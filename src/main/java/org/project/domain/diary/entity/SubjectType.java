package org.project.domain.diary.entity;

public enum SubjectType {
    SELECT("selected"), FREE("free");

    private final String type;
    SubjectType(String type) {
        this.type = type;
    }

    public static SubjectType of(String type) {
        switch (type) {
            case "selected":
                return SELECT;
            case "free":
                return FREE;
        }
        throw new IllegalArgumentException("SubjectType은 selected,free 중 하나입니다.");
    }
}
