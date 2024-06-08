package com.example.decofficial.ui.notices;

public class NoticesData {
    String title, image, date, time, key;

    public  NoticesData(){

    }

    public NoticesData(String title, String image, String date, String time, String key){
        this.time = time;
        this.title = title;
        this.date = date;
        this.image = image;
        this.key = key;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
