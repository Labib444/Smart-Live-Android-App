package com.example.livesmart.Weekdays_Schedule;

public class Data {

    String text;
    int progressValue;

    public Data(String text, int progressValue) {
        this.text = text;
        this.progressValue = progressValue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
    }
}
