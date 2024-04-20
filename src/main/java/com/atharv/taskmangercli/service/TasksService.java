package com.atharv.taskmangercli.service;

import com.atharv.taskmangercli.beans.Tasks;
import com.atharv.taskmangercli.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    private final TaskRepository repository;
    private static final String noRecords = "no record found";

    public TasksService(TaskRepository repository) {
        this.repository = repository;
    }

    public void addTask(int id, Tasks tasks) {
        repository.insert(id, tasks);
    }

    public void markTaskAsCompleted(int id) {
        if (repository.isId(id)) {
            repository.updateById(id);
            System.out.println("task marked as completed");
        }
        else System.out.println(noRecords);
    }

    public void removeTask(int id) {
        if (repository.isId(id)) {
            repository.deleteById(id);
            System.out.println("task removed successfully");
        }
        else System.out.println(noRecords);
    }

    public void getALlTask() {
        repository.readALl();
    }
}
