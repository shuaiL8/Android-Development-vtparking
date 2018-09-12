package com.example.fredliu.vtparking.Notification;

/**
 * Created by fredliu on 12/7/17.
 */

public class Detail {
    private String content;
    private String time;


    public Detail(String content, String time) {
        super();
        this.content = content;
        this.time = time;

    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
