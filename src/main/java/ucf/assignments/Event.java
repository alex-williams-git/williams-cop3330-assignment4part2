/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Alexander Williams
 */

package ucf.assignments;

import java.time.LocalDate;

public class Event {

    private String description;
    private LocalDate date;
    private String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event(LocalDate date, String description, String status)
    {
        this.setDate(date);
        this.setDescription(description);
        this.status = status;
    }
}
