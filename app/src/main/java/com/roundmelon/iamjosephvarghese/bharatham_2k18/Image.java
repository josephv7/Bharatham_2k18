package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import java.io.Serializable;


public class Image implements Serializable {
    private String name;
    private String small, medium, large;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String timestamp;
    private String id;

    public Image() {
    }

    public Image(String name, String small, String medium, String large, String timestamp) {
        this.name = name;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
    public void setImage(String small) {
        this.small = small;
        this.medium = small;
        this.large = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
