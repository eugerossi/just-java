package com.eugerossi.basics.resources;

import com.eugerossi.basics.Enums;

public class TaskWithStrategyPatternEnum {
    private Enums.StrategyPatternTaskStatus status;

    public Enums.StrategyPatternTaskStatus getStatus() {
        return status;
    }

    public void setStatus(Enums.StrategyPatternTaskStatus status) {
        this.status = status;
    }
}
