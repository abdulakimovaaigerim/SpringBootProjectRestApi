package peaksoft.service;

import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface CourseService {

    CourseResponse saveCourse(Long instructorId, Long companyId, CourseRequest courseRequest);

    List<CourseResponse> getAllCourse();

    CourseResponse getById(Long id);

    SimpleResponse updateCourse(Long id, CourseRequest courseRequest);

    SimpleResponse deleteById(Long id);

    List<CourseResponse> findAllSorted();

}
