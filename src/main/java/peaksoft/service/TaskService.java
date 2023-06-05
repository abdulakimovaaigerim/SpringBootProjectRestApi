package peaksoft.service;

import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.task.TaskRequest;
import peaksoft.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks(Long lessonId);

    SimpleResponse saveTask(Long lessonId, TaskRequest taskRequest);

    TaskResponse getById(Long id);

    SimpleResponse updateTask(Long id, TaskRequest taskRequest);
    public SimpleResponse deleteById(Long id);

    }
