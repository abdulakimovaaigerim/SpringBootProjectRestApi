package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.group.CreateGroupRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.student.StudentRequest;
import peaksoft.dto.student.StudentResponse;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentApi {

    private final StudentService studentService;

    @GetMapping("/{groupId}/getAll")
    public List<StudentResponse> getAll(@PathVariable Long groupId) {
        return studentService.getAllStudents(groupId);
    }

    @PostMapping("/{groupId}/save")
    public SimpleResponse save(@PathVariable Long groupId, @RequestBody StudentRequest studentRequest) {
        return studentService.saveStudent(groupId, studentRequest);
    }

    @GetMapping("/{id}")
    StudentResponse getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse update(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("/{id}/delete")
    public SimpleResponse delete(@PathVariable Long id) {
        return studentService.deleteById(id);
    }

    @PostMapping("/creat")
    public SimpleResponse createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        String groupName = createGroupRequest.getGroupName();
        List<Long> courseIds = createGroupRequest.getCourseIds();

        return studentService.createGroups(groupName, courseIds);
    }
    @PostMapping("/blocked/{studentId}")
    public SimpleResponse blockUnblockStudent(@PathVariable Long studentId, @RequestParam Boolean block){
        return studentService.blockUnblockStudent(studentId,block);
    }
    @GetMapping("/filter")
    public List<StudentResponse> getFilterStudent(@RequestParam String studyFormat){
        return studentService.filter(studyFormat);
    }
}
