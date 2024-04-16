package com.atharv.taskmangercli;

import com.atharv.taskmangercli.beans.Tasks;
import com.atharv.taskmangercli.service.TasksService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("com.atharv")
public class TaskMangerCliApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(TaskMangerCliApplication.class, args);

        TasksService tasksService = context.getBean(TasksService.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1. add task");
            System.out.println("2. mark task as completed");
            System.out.println("3. remove task");
            System.out.println("4. view all task");
            System.out.println("5. exit");
            System.out.println();

            int choice = 5;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e);
            }

            switch (choice) {
                case 1:
                    int switchChoice = 0;
                    String description = null;
                    String category = null;

                    System.out.println("Enter task title");
                    String title = scanner.nextLine();

                    System.out.println("you want to add description for the task?");
                    try {
                        switchChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    if (switchChoice == 1) {
                        System.out.println("Enter task description");
                        description = scanner.nextLine();
                    }

                    System.out.println("you want to add category for the task?");
                    try {
                        switchChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    if (switchChoice == 1) {
                        System.out.println("Enter any category of the task");
                        category = scanner.nextLine();
                    }

                    tasksService.addTask(new Tasks(title, description, category));
                    System.out.println("task successfully added");
                    break;

                case 2:
                    int completeIndex;
                    System.out.println("enter index of task to mark completed");
                    try {
                        completeIndex = scanner.nextInt();
                        tasksService.markTaskAsCompleted(completeIndex);
                        System.out.println("task marked as completed");
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("index not available");
                    }
                    break;

                case 3:
                    int removeIndex;
                    System.out.println("enter index of task to remove");
                    try {
                        removeIndex = scanner.nextInt();
                        tasksService.removeTask(removeIndex);
                        System.out.println("task removed successfully");
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("index not available");
                    }
                    break;

                case 4:
                    List<Tasks> allTask = tasksService.getALlTask();
                    if (allTask.isEmpty()) {
                        System.out.println("no task found");
                    } else {
                        for (int i = 0; i < allTask.size(); i++) {
                            Tasks tasks = allTask.get(i);
                            System.out.println("Task " + (i + 1) + "\nTitle: " + tasks.getTitle() + "\nDescription: " + tasks.getDescription() + "\nCategory: " + tasks.getCategory() + "\nStatus: " + tasks.isCompleted());
                            System.out.println();
                        }
                    }
                    break;

                case 5:
                    System.out.println("exiting..");
                    context.close();
                    scanner.close();
                    return;
                default:
                    System.out.println("invalid choice");

            }
        }

    }

}
