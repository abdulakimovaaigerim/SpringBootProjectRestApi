package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.task.TaskRequest;
import peaksoft.dto.task.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskApi {
    private final TaskService taskService;

    @GetMapping("/{lessonId}/getAll")
    public List<TaskResponse> getAll(@PathVariable Long lessonId) {
        return taskService.getAllTasks(lessonId);
    }

    @PostMapping("/{lessonId}/save")
    public SimpleResponse save(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId, taskRequest);
    }

    @GetMapping("/{id}")
    public TaskResponse getById(@PathVariable Long id){
        return taskService.getById(id);
    }

    @PutMapping("/{id}/update")
    public SimpleResponse update(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        return taskService.updateTask(id, taskRequest);
    }

}
