package peaksoft.service.impl;

import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<StudentResponse> getAllStudents(Long groupId) {
        return studentRepository.getAllStudents(groupId);
    }

    @Override
    public SimpleResponse saveStudent(Long groupId, StudentRequest studentRequest) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() ->
                    new NoSuchElementException("Group with id: " + groupId + " is not found!"));
            Student student = new Student();
            student.setFirstName(studentRequest.firstName());
            student.setLastName(studentRequest.lastName());
            student.setPhoneNumber(studentRequest.phoneNumber());
            student.setEmail(studentRequest.email());
            student.setStudyFormat(studentRequest.studyFormat());
            group.addStudent(student);
            student.setGroup(group);

            studentRepository.save(student);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY SAVED")
                    .message("Student with id: " + student.getFirstName() + " is saved")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to save student: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public StudentResponse getById(Long id) {
        try {
            return studentRepository.getStudentById(id).orElseThrow(() ->
                    new NoSuchElementException("Student with id: " + id + " is not found!"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to get student: " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse updateStudent(Long id, StudentRequest studentRequest) {
        try {
            Student student = studentRepository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Student with id: " + id + " is not found!"));

            student.setFirstName(studentRequest.firstName());
            student.setLastName(studentRequest.lastName());
            student.setPhoneNumber(studentRequest.phoneNumber());
            student.setEmail(studentRequest.email());
            student.setStudyFormat(studentRequest.studyFormat());
            studentRepository.save(student);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY UPDATE")
                    .message("Student with id: " + student.getFirstName() + " is updated")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to update student: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        try {
            studentRepository.deleteById(id);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY DELETE")
                    .message("Student with id: " + id + " is deleted")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get student: " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse createGroups(String groupName, List<Long> courseIds) {
        try {
            List<Course> courses = courseRepository.findAllById(courseIds);
            Group group = new Group();
            group.setGroupName(groupName);
            group.setCourses(courses);

            groupRepository.save(group);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY CREATED")
                    .message("Group with id: " + group.getGroupName() + " is created")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to created student: " + e.getMessage())
                    .build();

        }
    }

    @Override
    public SimpleResponse blockUnblockStudent(Long studentId, Boolean block) {
        try {
            Student student1 = studentRepository.findById(studentId)
                    .orElseThrow(() -> new NoSuchElementException("Student with id:" + studentId + "is not found"));
            student1.setIsBlocked(block);
            studentRepository.save(student1);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY").
                    message("Student with id:" + studentId + " is blocked")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to blocked student: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public List<StudentResponse> filter(String studyFormat) {
        try {
            Student student = new Student();
            if (studyFormat.equals("ONLINE")) {
                return studentRepository.getFilterOnLine();

            } else if (studyFormat.equals("OFFLINE")) {
                return studentRepository.getFilterOffLine();

            }
            return Collections.singletonList(StudentResponse.builder().
                    id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .email(student.getEmail())
                    .phoneNumber(student.getPhoneNumber())
                    .isBlocked(student.getIsBlocked())
                    .studyFormat(student.getStudyFormat())
                    .build());

        } catch (Exception e) {
            throw new RuntimeException("Failed to filter student: " + e.getMessage());

        }
    }
}