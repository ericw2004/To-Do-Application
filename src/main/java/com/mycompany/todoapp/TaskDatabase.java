/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.todoapp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import java.util.ArrayList;
import java.util.Collections;
import org.bson.Document;

/**
 *
 * @author Sophia Golden
 */
public class TaskDatabase {

    //big task arraylist
    ArrayList<Task> taskList = new ArrayList<Task>();

    //constructor i guess
    public TaskDatabase() {

        //this will add some basic tasks whether it's online or offline
        Task task1 = new Task(12, 45, "Fold laundry");
        Task task2 = new Task(10, 00, "Wash dishes");
        Task task3 = new Task(1, 30, "Homework");
        Task task4 = new Task(3, 50, "Workout");
        Task task5 = new Task(5, 50, "Make dinner");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
    }

    //get task list method - for the GUI so we can put it into a table
    //this is for the offline version
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    //add task method - takes in a string for the task text, the hour due, and the minute due
    public void addTask(String taskText, int hourDue, int minuteDue) {

        //create the object based on the given values and add it to the list
        Task task = new Task(hourDue, minuteDue, taskText);
        taskList.add(task);

    }

    //remove task by text method
    public void removeTaskByText(String text) {

        //temporary list
        ArrayList<Task> tempList = new ArrayList<Task>();

        //loop through the list and build a new one with everyone EXCEPT ones that match the text entered
        for (Task task : taskList) {
            if (!task.getTaskName().equalsIgnoreCase(text)) {
                tempList.add(task);
            }
        }

        //set the original list to be the new temporary list
        taskList = tempList;

    }

    //change task name text method - original text and new text can both be entered through the gui
    public void changeTaskName(String originalText, String newText) {

        //if it matches change it
        for (Task task : taskList) {
            if (task.getTaskName().equalsIgnoreCase(originalText)) {

                task.setTaskName(newText);

            }
        }
    }

    //complete text method - takes in the task text and sets it as completed
    public void completeTask(String taskText) {

        //if it matches change it
        for (Task task : taskList) {
            if (task.getTaskName().equalsIgnoreCase(taskText)) {

                task.setCompleted(true);

            }
        }
    }

    //update database method - takes all the tasks from the list and puts them into the mongodb database
    //this can be used to create a database as well
    //this is only for the online version
    //basically any time after adding or taking away a task, or changing a name or status, update the database
    public void updateDatabase() {

        //delete the current database so it resets, up here so it doesn't constantly reset
        MongoCollection collection = Mongo.getConnection().getDatabase("To-Do").getCollection("Tasks");;
        collection.drop();

        for (Task task : taskList) {

            Document doc1 = new Document("Task", task.getTaskName())
                    .append("Hour due", task.getHourDue())
                    .append("Minute due", task.getMinuteDue())
                    .append("Completed", task.isCompleted());

            InsertOneResult result = collection.insertOne(doc1);

        }

    }

    //clear database method - removes all the tasks from the database
    //works for both versions as long as you update the database with 
    public void clearDatabase() {
        taskList.clear();

    }

    //count tasks list - to see the number tasks from the gui
    //works for online and offline
    public int countTasks() {
        return taskList.size();

    }

    //count completed tasks
    public int countCompletedTasks() {
        int comp = 0;

        for (Task task : taskList) {

            if (task.isCompleted()) {
                comp += 1;
            }

        }

        return comp;

    }

    //sort method for fun
    public void sortTasks() {
        taskList.sort(null);

    }

}
