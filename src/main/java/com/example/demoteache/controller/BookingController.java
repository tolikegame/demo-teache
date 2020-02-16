package com.example.demoteache.controller;

import com.example.demoteache.model.request.AddRequest;
import com.example.demoteache.model.request.UpdateLongRequest;
import com.example.demoteache.model.request.UpdateRequest;
import com.example.demoteache.model.response.BookingDetailResponse;
import com.example.demoteache.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping(value = "/addBooking")
    public void addBooking(@RequestBody AddRequest addRequest){
        bookingService.addBooking(addRequest);
    }

    @PutMapping(value = "/updateBooking")
    public void updateBooking(@RequestBody UpdateRequest updateRequest){
        bookingService.updateBooking(updateRequest);
    }

    @PutMapping(value = "/updateLongBooking")
    public void updateLongBooking(@RequestBody UpdateLongRequest updateLongRequest){
        bookingService.updateLongBooking(updateLongRequest);
    }

    @DeleteMapping(value = "/deleteBooking")
    public void deleteBooking(int id){
        bookingService.deleteBooking(id);
    }

    @GetMapping(value = "getBookingDetail")
    public BookingDetailResponse getBookingDetail(int id){
        return bookingService.getBookingDetail(id);
    }

}
