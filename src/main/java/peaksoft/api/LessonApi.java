package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.lesson.LessonRequest;
import peaksoft.dto.lesson.LessonResponse;
import peaksoft.dto.lesson.UploadLessonRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.LessonService;

import java.util.List;


@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonApi {

    private final LessonService lessonService;

    @GetMapping("/{courseId}/getAll")
    public List<LessonResponse> getAll(@PathVariable Long courseId) {
        return lessonService.getAllLessons(courseId);
    }

    @PostMapping
    public SimpleResponse save(@RequestParam Long courseId,@RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(courseId, lessonRequest);
    }

    @GetMapping("/{id}")
    public LessonResponse getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse update(@Valid @PathVariable Long id, @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(id, lessonRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return lessonService.deleteById(id);
    }

    @PostMapping("/upload")
    public SimpleResponse uploadLesson(@RequestBody UploadLessonRequest uploadLessonRequest){
        String lessonName = uploadLessonRequest.getLessonName();
        Long courseId = uploadLessonRequest.getCourseId();
        return lessonService.uploadLessonToCourse(lessonName, courseId);
    }
}
