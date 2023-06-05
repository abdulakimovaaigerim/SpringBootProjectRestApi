package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseApi {
    private final CourseService courseService;

    @GetMapping
    public List<CourseResponse> getAll() {
        return courseService.getAllCourse();

    }

    @PostMapping("/{companyId}/{instructorId}/save")
    public CourseResponse save(@PathVariable Long instructorId, @PathVariable Long companyId, @RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(instructorId, companyId, courseRequest);
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse update(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);

    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return courseService.deleteById(id);
    }

    @GetMapping("/sorted")
    public List<CourseResponse> getAllCoursesSortedByDate() {
        return courseService.findAllSorted();
    }


}
