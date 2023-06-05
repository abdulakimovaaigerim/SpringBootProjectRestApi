package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.course.CourseRequest;
import peaksoft.dto.course.CourseResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.repository.CompanyRepository;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.InstructorRepository;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final InstructorRepository instructorRepository;

    @Override
    public CourseResponse saveCourse(Long instructorId, Long companyId, CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() ->
                    new NoSuchElementException("Company with id: " + companyId + " is not found!"));

            Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() ->
                    new NoSuchElementException("Instructor with id: " + instructorId + " is not found!"));

            Course course = new Course();
            course.setCourseName(courseRequest.courseName());
            course.setDateOfStart(courseRequest.dateOfStart());
            course.setDescription(courseRequest.description());
            company.addCourse(course);
            course.setCompany(company);
            instructor.addCourse(course);
            course.setInstructor(instructor);

            courseRepository.save(course);

            return new CourseResponse(course.getId(),
                    course.getCourseName(), course.getDateOfStart(), course.getDescription());

        } catch (Exception e) {
            throw new RuntimeException("Failed to save course: " + e.getMessage());
        }
    }

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseRepository.getAllCourses();
    }

    @Override
    public CourseResponse getById(Long id) {
        try {
            return courseRepository.getCourseById(id).orElseThrow(() ->
                    new NoSuchElementException("Course with id: " + id + " is not found!"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve course: " + e.getMessage());
        }
    }

    @Override
    public SimpleResponse updateCourse(Long id, CourseRequest courseRequest) {
        try {
            Course course = courseRepository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Course with id: " + id + " is not found!"));

            course.setCourseName(courseRequest.courseName());
            course.setDateOfStart(courseRequest.dateOfStart());
            course.setDescription(courseRequest.description());
            courseRepository.save(course);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY UPDATE!")
                    .message("Course with id: " + course.getCourseName() + " is updated!")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to update course: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        try {
            courseRepository.deleteById(id);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY DELETED!")
                    .message("Course with id: " + id + " is deleted!")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete company: " + e.getMessage());
        }
    }

    @Override
    public List<CourseResponse> findAllSorted() {
        try {
            return courseRepository.findAllSortedByDate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve sorted courses: " + e.getMessage());
        }
    }

}
