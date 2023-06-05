package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.student.StudentResponse;
import peaksoft.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select new peaksoft.dto.student.StudentResponse(s.id,s.firstName,s.lastName,s.email,s.phoneNumber,s.studyFormat, s.isBlocked)  from Student  s where s.group.id=:groupId")
    List<StudentResponse> getAllStudents(Long groupId);

    Optional<StudentResponse> getStudentById(Long id);

    @Query("SELECT new peaksoft.dto.student.StudentResponse(s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.studyFormat, s.isBlocked) FROM Student s WHERE s.studyFormat='ONLINE'")
    List<StudentResponse> getFilterOnLine();

    @Query("SELECT new peaksoft.dto.student.StudentResponse(s.id, s.firstName, s.lastName, s.email, s.phoneNumber, s.studyFormat, s.isBlocked) FROM Student s WHERE s.studyFormat='OFFLINE'")
    List<StudentResponse> getFilterOffLine();}
