package com.eugerossi.basics.resources;

import com.eugerossi.basics.Enums;

public class Task {
    private Enums.TaskStatus status;

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public Enums.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.TaskStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
