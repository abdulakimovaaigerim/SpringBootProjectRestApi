package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.course.CourseResponse;
import peaksoft.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new peaksoft.dto.course.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description) from Course c")
    List<CourseResponse> getAllCourses();

    Optional<CourseResponse> getCourseById(Long id);

    @Query("select new peaksoft.dto.course.CourseResponse(c.id,c.courseName,c.dateOfStart,c.description) from Course  c order by c.dateOfStart")
    List<CourseResponse> findAllSortedByDate();

}
