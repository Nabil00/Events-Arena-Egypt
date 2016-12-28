package com.arena_egypt.nabil.events.entities;


public class Event {
    private String name;
    private String location;
    private String description;
    private String date;

    public Event() {
    }

    public Event(String name, String location, String description, String date) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
