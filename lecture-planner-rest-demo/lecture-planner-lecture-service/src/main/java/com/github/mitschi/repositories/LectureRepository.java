package com.github.mitschi.repositories;

import com.github.mitschi.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    @Query("SELECT COUNT(lecture) FROM Lecture lecture WHERE lecture.lecturerId=?1")
    long test(long id);
}
