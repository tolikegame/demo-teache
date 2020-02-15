package com.example.demoteache.service;

import com.example.demoteache.model.po.Booking;
import com.example.demoteache.model.po.Dates;
import com.example.demoteache.model.po.Rooms;
import com.example.demoteache.model.po.Users;
import com.example.demoteache.model.request.AddRequest;
import com.example.demoteache.repository.BookingRepository;
import com.example.demoteache.repository.RoomsRepository;
import com.example.demoteache.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    UsersRepository usersRepository;


    @Override
    @Transactional
    public void addBooking(AddRequest addRequest) {
        Booking booking = new Booking();

//        //建立者
//        Users user = usersRepository.getOne(addRequest.getCreator());
//        booking.setCreator(user);

        //會議室
        Rooms rooms = roomsRepository.getOne(addRequest.getRoom());
        booking.setRooms(rooms);

        //用途
        booking.setTheme(addRequest.getTheme());

        //相關人
        List<Users> usersList = new ArrayList<>();
        //建立人
        Users creator = usersRepository.getOne(addRequest.getCreator());
        usersList.add(creator);
        //參加人
        for(Integer joinUserId : addRequest.getJoinUser()){
            Users joinUser = usersRepository.getOne(joinUserId);
            usersList.add(joinUser);
        }
        booking.setJoinUser(usersList);
        booking.setLongBooking(addRequest.isLongBooking());

        bookingRepository.save(booking);

        //日期+時間
        List<Dates> datesList = new ArrayList<>();
        if(addRequest.isLongBooking()){
            Dates bookingDate = new Dates();
            bookingDate.setDate(addRequest.getDate());
            bookingDate.setSartTime(addRequest.getStartTime());
            bookingDate.setEndTime(addRequest.getEndTime());
            bookingDate.setBooking(booking);
            datesList.add(bookingDate);
            for(int i=1; i<12; i++){
                Dates date = new Dates();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE,+7*i);
                Date afterToSeven = calendar.getTime();
//                calendar.set(Calendar.DAY_OF_WEEK,calendar.get(addRequest.getDate()+7));
//                Date date1 = addRequest.getDate();
                date.setDate(afterToSeven);
                date.setSartTime(addRequest.getStartTime());
                date.setEndTime(addRequest.getEndTime());
                date.setBooking(booking);
                datesList.add(date);
                booking.setDateList(datesList);
            }
        }else {
            Dates date = new Dates();
            date.setDate(addRequest.getDate());
            date.setSartTime(addRequest.getStartTime());
            date.setEndTime(addRequest.getEndTime());
            date.setBooking(booking);
            datesList.add(date);
            booking.setDateList(datesList);
        }

    }
}
