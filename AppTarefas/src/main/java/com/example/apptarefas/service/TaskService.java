package com.example.apptarefas.service;

import com.example.apptarefas.model.Task;
import com.example.apptarefas.model.User;
import com.example.apptarefas.repository.TaskRepository;
import com.example.apptarefas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveTask(Task task, String username) {
        User user = userRepository.findByUsername(username)
                                   .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        logger.info("Saving task: {}", task);
        taskRepository.save(task);
        logger.info("Task saved successfully");
    }

    public List<Task> getTasksByUsername(String username) {
        return taskRepository.findByUserUsername(username);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Transactional
    public void updateTask(Task task, String username) {
        Task existingTask = getTaskById(task.getId());
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setCompleted(task.isCompleted());
        taskRepository.save(existingTask);
        logger.info("Updated task: {}", existingTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        logger.info("Deleting task with id: {}", id);
        taskRepository.deleteById(id);
        logger.info("Task deleted successfully");
    }
}
