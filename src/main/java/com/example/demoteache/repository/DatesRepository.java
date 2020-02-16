package com.example.demoteache.repository;

import com.example.demoteache.model.po.Booking;
import com.example.demoteache.model.po.Dates;
import com.example.demoteache.model.request.UpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatesRepository extends JpaRepository<Dates,Integer> {

//    @Query("select d from Dates d where d.booking = ?1")
    Dates findByBooking(Booking booking);

    List<Dates> findAllByBooking(Booking booking);

}
