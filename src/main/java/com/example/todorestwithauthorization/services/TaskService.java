package com.example.todorestwithauthorization.services;

import com.example.todorestwithauthorization.models.Task;
import com.example.todorestwithauthorization.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> findAllByUserId(long id) {
        return taskRepository.findAllByUserId(id);
    }
    public void save(Task task){
        taskRepository.save(task);
    }
    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }
    public void delete(long id) {
        taskRepository.deleteById(id);
    }
    public void update(Task task) {
        taskRepository.save(task);
    }
}
