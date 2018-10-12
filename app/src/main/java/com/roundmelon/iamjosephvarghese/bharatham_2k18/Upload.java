package com.roundmelon.iamjosephvarghese.bharatham_2k18;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Upload{

    public String name;
    public String url;
    public int status;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String url,int status) {
        this.name = name;
        this.url= url;
        this.status=status;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
