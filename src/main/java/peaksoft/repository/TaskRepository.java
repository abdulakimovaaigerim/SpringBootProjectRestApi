package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.task.TaskResponse;
import peaksoft.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select new peaksoft.dto.task.TaskResponse(t.id,t.taskName,t.taskText,t.deadline) from Task t where t.lesson.id=:lessonId")
    List<TaskResponse> getAllTasks(Long lessonId);

    Optional<TaskResponse> getTaskById(Long id);
}
