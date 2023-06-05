package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.task.TaskRequest;
import peaksoft.dto.task.TaskResponse;
import peaksoft.entities.Lesson;
import peaksoft.entities.Task;
import peaksoft.repository.LessonRepository;
import peaksoft.repository.TaskRepository;
import peaksoft.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;

    @Override
    public List<TaskResponse> getAllTasks(Long lessonId) {
        try {
            lessonRepository.findById(lessonId).orElseThrow(() ->
                    new NoSuchElementException("Lesson with id: " + lessonId + " is not found!"));

            return taskRepository.getAllTasks(lessonId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve task for lesson: " + lessonId + ". " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() ->
                    new NoSuchElementException("Lesson with id: " + lessonId + "  is not found!"));

            Task task = new Task();
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadline(taskRequest.deadline());
            lesson.addTask(task);
            task.setLesson(lesson);

            taskRepository.save(task);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY SAVED")
                    .message("Task with id: " + task.getTaskName() + " is saved!")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to save task: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public TaskResponse getById(Long id) {
        try {
            return taskRepository.getTaskById(id).orElseThrow(() ->
                    new NoSuchElementException("Task with id: " + id + " is not found!"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to get lesson: " + e.getMessage());

        }
    }

    @Override
    public SimpleResponse updateTask(Long id, TaskRequest taskRequest) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Task with id: " + id + " is not found!"));
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadline(taskRequest.deadline());
            taskRepository.save(task);

            return SimpleResponse.builder()
                    .status("SUCCESSFULLY UPDATE")
                    .message("Task with id: " + task.getTaskName() + " is updated!")
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status("ERROR")
                    .message("Failed to update task: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteById(Long id) {
       try {
           taskRepository.findById(id)
                   .orElseThrow(() -> new NoSuchElementException("Task with id:" + id + " not found"));
           taskRepository.deleteById(id);

           return SimpleResponse.builder()
                   .status("SUCCESSFULLY DELETE")
                   .message("successfully deleted!")
                   .build();

       }catch (Exception e){
           return SimpleResponse.builder()
                   .status("ERROR")
                   .message("Failed delete task: " + e.getMessage())
                   .build();
       }
    }
}
