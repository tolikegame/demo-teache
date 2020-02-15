package com.example.demoteache.model.po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "users")
//    private List<Booking> userBookingList;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "booking_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "booking_id")})
    private List<Booking> joinBookingList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Booking> getUserBookingList() {
//        return userBookingList;
//    }
//
//    public void setUserBookingList(List<Booking> userBookingList) {
//        this.userBookingList = userBookingList;
//    }

    public List<Booking> getJoinBookingList() {
        return joinBookingList;
    }

    public void setJoinBookingList(List<Booking> joinBookingList) {
        this.joinBookingList = joinBookingList;
    }
}
