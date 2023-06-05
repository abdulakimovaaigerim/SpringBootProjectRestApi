package peaksoft.service;

import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;

import java.util.List;

public interface StudentService {

    List<StudentResponse> getAllStudents(Long groupId);

    SimpleResponse saveStudent(Long groupId, StudentRequest studentRequest);

    StudentResponse getById(Long id);

    SimpleResponse updateStudent(Long id, StudentRequest studentRequest);

    SimpleResponse deleteById(Long id);

    SimpleResponse createGroups(String groupName, List<Long> courseIds);

    SimpleResponse blockUnblockStudent(Long studentId, Boolean block);

    List<StudentResponse> filter(String studyFormat);
}
