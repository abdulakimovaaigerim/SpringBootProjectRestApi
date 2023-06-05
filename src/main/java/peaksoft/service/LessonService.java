package peaksoft.service;

import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;


public interface LessonService {

    List<LessonResponse> getAllLessons(Long courseId);

    SimpleResponse saveLesson(Long courseId, LessonRequest lessonRequest);

    LessonResponse getById(Long id);

    SimpleResponse updateLesson(Long id, LessonRequest lessonRequest);

    SimpleResponse deleteById(Long id);


    SimpleResponse uploadLessonToCourse(String lessonName, Long courseId);
}
