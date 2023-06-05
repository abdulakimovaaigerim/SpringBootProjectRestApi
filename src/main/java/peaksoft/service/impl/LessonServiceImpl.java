package peaksoft.service.impl;

import jakarta.persistence.Enumerated;
import org.hibernate.query.NotIndexedCollectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entities.Course;
import peaksoft.entities.Lesson;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<LessonResponse> getAllLessons(Long courseId) {
        try {
            courseRepository.findById(courseId).orElseThrow(() ->
                    new NoSuchElementException("Course with id: " + courseId + " is not found!"));
            return lessonRepository.getAllLessons(courseId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve lessons for course: " + courseId + ". " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() ->
                    new NoSuchElementException("Course with id: " + courseId + " is not found!"));

            Lesson lesson = new Lesson();
            lesson.setLessonName(lessonRequest.lessonName());

            course.addLesson(lesson);
            lesson.setCourse(course);

            lessonRepository.save(lesson);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY SAVED")
                    .message("Lesson with id: " + lesson.getLessonName() + " is saved")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to save lesson: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public LessonResponse getById(Long id) {
        try {
            return lessonRepository.getLessonById(id).orElseThrow(() ->
                    new NoSuchElementException("Lesson with id: " + id + " is not found!"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to get lesson: " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse updateLesson(Long id, LessonRequest lessonRequest) {
        try {
            Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                    new NotIndexedCollectionException("Lesson with id: " + id + " is not found!"));

            lesson.setLessonName(lessonRequest.lessonName());
            lessonRepository.save(lesson);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY UPDATE")
                    .message("Lesson with id: " + lesson.getLessonName() + " is updated")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to update lesson: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        try {
            lessonRepository.deleteById(id);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY DELETE")
                    .message("Lesson with id: " + id + " is deleted")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get lesson: " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse uploadLessonToCourse(String lessonName, Long courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() ->
                    new NoSuchElementException("Course with id: " + courseId + " is not found!"));

            Lesson lesson = new Lesson();
            lesson.setLessonName(lessonName);
            lesson.setCourse(course);
            lessonRepository.save(lesson);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY LOADED")
                    .message("Lesson with id: " + lessonName + " is loaded")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to upload lesson: " + e.getMessage())
                    .build();
        }
    }
}