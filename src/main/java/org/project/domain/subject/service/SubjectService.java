package org.project.domain.subject.service;

import lombok.RequiredArgsConstructor;
import org.project.domain.subject.dto.response.SubjectResponse;
import org.project.domain.subject.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectResponse getSubject() {

        // 최신 content 하나 가져오기
        String content = subjectRepository.findFirstByOrderByIdDesc()
                .getContent();

        return SubjectResponse.of(content);
    }
}
