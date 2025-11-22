package org.project.domain.subject.dto.response;

public record SubjectResponse(
        String content
) {
    public static SubjectResponse of(String content) {
        return new SubjectResponse(content);
    }
}
