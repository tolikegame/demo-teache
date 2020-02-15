package com.example.demoteache.controller;

import com.example.demoteache.model.request.AddRequest;
import com.example.demoteache.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping(value = "/addBooking")
    public void addBooking(@RequestBody AddRequest addRequest){
        bookingService.addBooking(addRequest);
    }


}
