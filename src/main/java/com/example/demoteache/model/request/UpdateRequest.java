package com.example.demoteache.model.request;

import java.util.Date;
import java.util.List;

public class UpdateRequest {

    private int bookingId;
    private int room;
    private int creator;
    private Date date;
    private String startTime;
    private String endTime;
    private String theme;
    private List<Integer> joinUser;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Integer> getJoinUser() {
        return joinUser;
    }

    public void setJoinUser(List<Integer> joinUser) {
        this.joinUser = joinUser;
    }
}
