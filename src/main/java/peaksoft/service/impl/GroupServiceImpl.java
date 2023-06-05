package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import peaksoft.dto.group.GroupRequest;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupStudentCountResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.repository.CourseRepository;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;
import peaksoft.service.GroupService;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<GroupResponse> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    @Override
    public SimpleResponse saveGroup(GroupRequest groupRequest) {
        try {
            Group group = new Group();
            group.setGroupName(groupRequest.groupName());
            group.setImageLink(groupRequest.imageLink());
            group.setDescription(groupRequest.description());

            groupRepository.save(group);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY SAVED")
                    .message("Group with id: " + group.getGroupName() + " is saved!")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to save group: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public GroupResponse getGroupById(Long id) {
        groupRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Group with id: " + id + " is not found"));

        return groupRepository.getGroupById(id);
    }

    @Override
    public SimpleResponse updateGroup(Long id, GroupRequest groupRequest) {
        try {
            Group group = groupRepository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Group with id: " + id + " is not found!"));

            group.setGroupName(groupRequest.groupName());
            group.setImageLink(groupRequest.imageLink());
            group.setDescription(groupRequest.description());
            groupRepository.save(group);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY UPDATED")
                    .message("Group with id: " + group.getGroupName() + " is updated ")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to update group: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteGroupById(Long id) {
        try {
            groupRepository.deleteById(id);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY DELETED")
                    .message("Group with id: " + id + " is deleted ")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete group: " + e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public SimpleResponse assignGroup(Long courseId, Long groupId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() ->
                    new NoSuchElementException("Group with id: " + groupId + " is not found!"));
            Course course = courseRepository.findById(courseId).orElseThrow(() ->
                    new NoSuchElementException("Course with id: " + courseId + " is not found!"));
            if (course.getGroups() != null) {
                for (Group courseGroup : course.getGroups()) {
                    if (courseGroup.getId().equals(groupId)) {
                        throw new IOException("This group already exists!");
                    }
                }
                group.addCourse(course);
                course.addGroup(group);
                groupRepository.save(group);
                courseRepository.save(course);
            }
            return SimpleResponse.builder()
                    .status("SUCCESSFULLY ASSIGN")
                    .message("Group with id : " + groupId + "  " + "Course with id: " + courseId + " successfully assign")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to assign group: " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse addStudentToGroup(Long groupId, Long studentId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() ->
                    new NoSuchElementException("Group with id: " + groupId + " is not found!"));

            Student student = studentRepository.findById(studentId).orElseThrow(() ->
                    new NoSuchElementException("Student with id: " + studentId + " is not found!"));

            if (student.getGroup() != null && student.getGroup().equals(group.getId())) {
                throw new IllegalArgumentException("The student is already a member of this group!");
            }

            student.setGroup(group);
            group.getStudents().add(student);
            studentRepository.save(student);
            groupRepository.save(group);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY JOINED")
                    .message("Group with id : " + group.getId() + "  " + "Student with id: " + student.getId() + " successfully joined")
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Failed to add joined: " + e.getMessage());

        }
    }

    @Override
    public GroupStudentCountResponse getStudentCountByGroup(Long groupId) {
        try {
            return groupRepository.getStudentCountByGroup(groupId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve student count for group: " + groupId + ". " + e.getMessage());
        }

    }
}
