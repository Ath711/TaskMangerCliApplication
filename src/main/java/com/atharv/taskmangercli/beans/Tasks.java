package com.atharv.taskmangercli.beans;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tasks {
    private String title;
    private String description;

    private boolean completed;

    private String category;

    public Tasks(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
}
