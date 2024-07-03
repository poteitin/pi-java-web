package com.example.apptarefas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.apptarefas.model.Task;
import com.example.apptarefas.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String taskList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("tasks", taskService.getTasksByUsername(username));
        return "task-list";
    }

    @GetMapping("/add-task")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/save-task")
    public String saveTask(@ModelAttribute("task") Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        taskService.saveTask(task, username);
        return "redirect:/tasks";
    }

    @GetMapping("/edit-task/{id}")
    public String editTaskForm(@PathVariable("id") Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }

    @PostMapping("/update-task")
    public String updateTask(@ModelAttribute("task") Task task) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        taskService.updateTask(task, username);
        return "redirect:/tasks";
    }

    @PostMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }
}
