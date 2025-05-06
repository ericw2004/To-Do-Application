/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoapp;

import java.io.Serializable;

/**
 *
 * @author Sophia Golden
 */
public class Task implements Serializable{

    //time due integer - this may be the entirely wrong way to set up the time 
    private int hourDue;
    private int minuteDue;

    //task name string
    private String taskName;

    //completed boolean
    private boolean completed = false;

    //constructor
    public Task(int dueHour, int dueMinute, String taskText) {

        this.hourDue = dueHour;
        this.minuteDue = dueMinute;
        this.taskName = taskText;

    }

    //getters and setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getHourDue() {
        return hourDue;
    }

    public int getMinuteDue() {
        return minuteDue;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
