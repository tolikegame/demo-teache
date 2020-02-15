package com.example.demoteache.model.po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "is_long_booking")
    private boolean isLongBooking;


//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "creator_id")
//    private Users creator;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "booking_user",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<Users> joinUser;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "booking")
    private List<Dates> dateList;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id")
    private Rooms rooms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isLongBooking() {
        return isLongBooking;
    }

    public void setLongBooking(boolean longBooking) {
        isLongBooking = longBooking;
    }

//    public Users getCreator() {
//        return creator;
//    }
//
//    public void setCreator(Users creator) {
//        this.creator = creator;
//    }

    public List<Users> getJoinUser() {
        return joinUser;
    }

    public void setJoinUser(List<Users> joinUser) {
        this.joinUser = joinUser;
    }

    public List<Dates> getDateList() {
        return dateList;
    }

    public void setDateList(List<Dates> dateList) {
        this.dateList = dateList;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
}
