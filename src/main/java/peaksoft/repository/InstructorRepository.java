package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.entities.Instructor;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    @Query("select new peaksoft.dto.instructor.InstructorResponse(i.id, i.firstName, i.lastName, i.phoneNumber, i.specialization) from Instructor i")
    List<InstructorResponse> getAllInstructors();

    @Query("select new peaksoft.dto.instructor.InstructorResponse(i.id,i.firstName,i.lastName,i.phoneNumber,i.specialization) from Instructor i where i.id=:id")
    InstructorResponse getInstructorById(Long id);
}
