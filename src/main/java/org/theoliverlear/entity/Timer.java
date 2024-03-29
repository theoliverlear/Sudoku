package org.theoliverlear.entity;

public class Timer {
    //=============================-Variables-================================
    private String time;
    private int seconds;
    private int minutes;
    //============================-Constructors-==============================
    public Timer() {
        this.time = "00:00";
        this.seconds = 0;
        this.minutes = 0;
    }
    public Timer(String time) {
        this.time = time;
        String[] timeArray = time.split(":");
        this.minutes = Integer.parseInt(timeArray[0]);
        this.seconds = Integer.parseInt(timeArray[1]);
    }
    public Timer(int seconds, int minutes) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.time = String.format("%02d:%02d", minutes, seconds);
    }
    //=============================-Getters-==================================
    public String getTime() {
        return this.time;
    }
    public int getSeconds() {
        return this.seconds;
    }
    public int getMinutes() {
        return this.minutes;
    }
    //=============================-Setters-==================================
    public void setTime(String time) {
        this.time = time;
    }
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
