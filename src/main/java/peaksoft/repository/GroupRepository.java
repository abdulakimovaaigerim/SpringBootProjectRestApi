package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.dto.group.GroupResponse;
import peaksoft.dto.group.GroupStudentCountResponse;
import peaksoft.entities.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select new peaksoft.dto.group.GroupResponse (g.id,g.groupName,g.imageLink,g.description) from Group g")
    List<GroupResponse> getAllGroups();

    @Query("select new peaksoft.dto.group.GroupResponse(g.id,g.groupName,g.imageLink,g.description) from Group g where g.id=:id")
    GroupResponse getGroupById(Long id);


    @Query("select new peaksoft.dto.group.GroupStudentCountResponse(g.id, count (s)) from Group  g join g.students s where g.id=:groupId")
    GroupStudentCountResponse getStudentCountByGroup(@Param("groupId") Long groupId);
}