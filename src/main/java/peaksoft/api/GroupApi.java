package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupStudentCountResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupApi {

    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> getAll() {
        return groupService.getAllGroups();
    }

    @PostMapping
    public SimpleResponse save(@RequestBody GroupRequest groupRequest) {
        return groupService.saveGroup(groupRequest);
    }

    @GetMapping("/{id}")
    public GroupResponse getById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody GroupRequest groupRequest) {
        return groupService.updateGroup(id, groupRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteById(@PathVariable Long id) {
        return groupService.deleteGroupById(id);
    }

    @PostMapping("/{courseId}/{groupId}/assignCourseIdByGroupId")
    public SimpleResponse assign(@PathVariable Long courseId, @PathVariable Long groupId){
        return groupService.assignGroup(courseId, groupId);
    }

    @PostMapping("/{groupId}/students/{studentId}")
    public SimpleResponse addStudent(@PathVariable Long groupId, @PathVariable Long studentId){
        return groupService.addStudentToGroup(groupId, studentId);
    }

    @GetMapping("/{groupId}/students/count")
    public GroupStudentCountResponse getStudentCountByGr(@PathVariable Long groupId){
        return groupService.getStudentCountByGroup(groupId);
    }
}
