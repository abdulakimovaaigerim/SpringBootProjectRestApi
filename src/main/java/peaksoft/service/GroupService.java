package peaksoft.service;

import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupStudentCountResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface GroupService {

    List<GroupResponse> getAllGroups();

    SimpleResponse saveGroup(GroupRequest groupRequest);

    GroupResponse getGroupById(Long id);

    SimpleResponse updateGroup(Long id, GroupRequest groupRequest);

    SimpleResponse deleteGroupById(Long id);

    SimpleResponse assignGroup(Long courseId, Long groupId);

    SimpleResponse addStudentToGroup(Long groupId, Long studentId);

    GroupStudentCountResponse getStudentCountByGroup(Long groupId);

}
