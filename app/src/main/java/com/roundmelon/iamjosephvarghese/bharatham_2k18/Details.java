package com.roundmelon.iamjosephvarghese.bharatham_2k18;

public class Details {
    private String name;
    private String className;
    private String house;
    private String score;
    private String eventName;
    private String position;
    private String uploader;

    public Details(){
        this.name = name;
        this.house = house;
        this.score = score;
        this.eventName = eventName;
        this.position = position;
        this.className = className;
        this.uploader = uploader;
    }

    public Details(String name, String className, String house, String score, String eventName, String position, String uploader) {
        this.name = name;
        this.className = className;
        this.house = house;
        this.score = score;
        this.eventName = eventName;
        this.position = position;
        this.uploader = uploader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
