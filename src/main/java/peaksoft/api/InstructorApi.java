package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.instructor.InstructorRequest;
import peaksoft.dto.instructor.InstructorResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.InstructorService;

import java.util.List;


@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorApi {

    private final InstructorService instructorService;

    @GetMapping
    public List<InstructorResponse> getAll() {
        return instructorService.getAllInstructors();
    }

    @PostMapping("/save")
    public SimpleResponse saveInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.saveInstructor(instructorRequest);
    }

    @GetMapping("/{id}")
    public InstructorResponse getById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(id, instructorRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return instructorService.deleteInstructorById(id);
    }

    @PostMapping("/{companyId}/{instructorId}/assignCompanyIdByInstructorId")
    public SimpleResponse assign(@PathVariable Long companyId, @PathVariable Long instructorId){
        return instructorService.assignInstructor(companyId, instructorId);
    }


}
