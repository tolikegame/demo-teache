package com.example.demoteache.service;

import com.example.demoteache.model.request.AddRequest;
import com.example.demoteache.model.request.UpdateLongRequest;
import com.example.demoteache.model.request.UpdateRequest;
import com.example.demoteache.model.response.BookingDetailResponse;

import java.util.List;

public interface BookingService {
    void addBooking(AddRequest addRequest);

    void updateBooking(UpdateRequest updateRequest);

    void updateLongBooking(UpdateLongRequest updateLongRequest);

    void deleteBooking(int id);

    BookingDetailResponse getBookingDetail(int id);
}
