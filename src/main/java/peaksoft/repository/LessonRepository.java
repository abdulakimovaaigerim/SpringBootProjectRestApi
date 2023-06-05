package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.entities.Lesson;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select new peaksoft.dto.lesson.LessonResponse(l.id,l.lessonName) from Lesson l where l.course.id=:courseId")
    List<LessonResponse> getAllLessons(@Param("courseId") Long courseId);

    Optional<LessonResponse> getLessonById(Long id);
}
