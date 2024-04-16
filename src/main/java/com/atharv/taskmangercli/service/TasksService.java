package com.atharv.taskmangercli.service;

import com.atharv.taskmangercli.beans.Tasks;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TasksService {
    private List<Tasks> taskList = new ArrayList<>();

    public void addTask(Tasks tasks){
        taskList.add(tasks);
    }

    public void markTaskAsCompleted(int index){
        taskList.get(index).setCompleted(true);
    }

    public void removeTask(int index){
        taskList.remove(index);
    }

    public List<Tasks> getALlTask(){
        return taskList;
    }

}
