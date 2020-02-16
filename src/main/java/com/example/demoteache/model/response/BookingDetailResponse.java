package com.example.demoteache.model.response;

import java.util.Date;
import java.util.List;

public class BookingDetailResponse {

    private String roomName;
    private Date date;
    private String startTime;
    private String endTime;
    private String theme;
    private List<String> joinUser;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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

    public List<String> getJoinUser() {
        return joinUser;
    }

    public void setJoinUser(List<String> joinUser) {
        this.joinUser = joinUser;
    }
}
