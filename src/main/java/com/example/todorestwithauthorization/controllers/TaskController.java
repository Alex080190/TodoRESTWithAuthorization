package com.example.todorestwithauthorization.controllers;

import com.example.todorestwithauthorization.models.Task;
import com.example.todorestwithauthorization.models.User;
import com.example.todorestwithauthorization.services.TaskService;
import com.example.todorestwithauthorization.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }
    @GetMapping
    public List<Task> findAll() {
        return taskService.findAllByUserId(getUserId());
    }
    @PostMapping("/add")
    public String save(@RequestBody Task task) {
        task.setUserId(getUserId());
        taskService.save(task);
        return "Task with description: \"" +  task.getDescription() + "\" added";
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable("id") long id) {
        return taskService.findById(id).get();
    }
    @PatchMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @RequestBody Task task) {
        Task updatedTask = taskService.findById(id).get();
        updatedTask.setCompleted(task.isCompleted());
        updatedTask.setDescription(task.getDescription());
        taskService.update(updatedTask);
        return "Task with id: " + id + " updated";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return "Task with id: " + id + " was deleted";
    }

    private long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userService.findByName(authentication.getName());
        return user.get().getId();
    }
}
