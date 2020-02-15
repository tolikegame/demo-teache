package com.example.demoteache.model.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "date")
public class Dates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "sart_time")
    private String sartTime;

    @Column(name = "end_time")
    private String endTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSartTime() {
        return sartTime;
    }

    public void setSartTime(String sartTime) {
        this.sartTime = sartTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
