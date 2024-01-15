package org.theoliverlear.comm.timer;

public class TimerRequest {
    String timer;
    //============================-Constructors-==============================
    public TimerRequest() {
        this.timer = "00:00";
    }
    public TimerRequest(String timer) {
        this.timer = timer;
    }
    //=============================-Getters-==================================
    public String getTimer() {
        return this.timer;
    }
    //=============================-Setters-==================================
    public void setTimer(String timer) {
        this.timer = timer;
    }
}
