package org.theoliverlear.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Timer {
    //=============================-Variables-================================
    @Id
    private String time;
    private int seconds;
    private int minutes;
    //============================-Constructors-==============================
    public Timer() {
        this.time = "00:00";
        this.seconds = 0;
        this.minutes = 0;
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
