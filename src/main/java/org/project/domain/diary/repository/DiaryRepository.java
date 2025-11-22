package org.project.domain.diary.repository;

import org.project.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query(value = "SELECT * FROM diary LIMIT 1 OFFSET :offset", nativeQuery = true)
    Optional<Diary> findRandomDiary(@Param("offset") int offset);

    List<Diary> findAllByUserId(Long userId);
}
