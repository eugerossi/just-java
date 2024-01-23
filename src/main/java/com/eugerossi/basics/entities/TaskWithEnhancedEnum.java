package com.eugerossi.basics.resources;

import com.eugerossi.basics.Enums;

public class TaskWithEnhancedEnum {
    private Enums.EnhancedTaskStatus status;

    public Enums.EnhancedTaskStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.EnhancedTaskStatus status) {
        this.status = status;
    }
}
