package com.example.fredliu.vtparking.Me;

/**
 * Created by fredliu on 12/8/17.
 */

public class HistoryDetail {

    private String content;
    private String time;


    public HistoryDetail( String content, String time) {
        super();
        this.content = content;
        this.time = time;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String lot) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}