package peaksoft.service;

import peaksoft.dto.instructor.InstructorRequest;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface InstructorService {

    List<InstructorResponse> getAllInstructors();

    SimpleResponse saveInstructor(InstructorRequest instructorRequest);

    InstructorResponse getInstructorById(Long id);

    SimpleResponse updateInstructor(Long id, InstructorRequest instructorRequest);

    SimpleResponse deleteInstructorById(Long id);

    SimpleResponse assignInstructor(Long companyId, Long instructorId);


    }
