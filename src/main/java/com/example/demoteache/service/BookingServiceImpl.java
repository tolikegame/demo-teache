package com.example.demoteache.service;

import com.example.demoteache.model.po.Booking;
import com.example.demoteache.model.po.Dates;
import com.example.demoteache.model.po.Rooms;
import com.example.demoteache.model.po.Users;
import com.example.demoteache.model.request.AddRequest;
import com.example.demoteache.model.request.UpdateLongRequest;
import com.example.demoteache.model.request.UpdateRequest;
import com.example.demoteache.model.response.BookingDetailResponse;
import com.example.demoteache.repository.BookingRepository;
import com.example.demoteache.repository.DatesRepository;
import com.example.demoteache.repository.RoomsRepository;
import com.example.demoteache.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    DatesRepository datesRepository;


    @Override
    @Transactional
    public void addBooking(AddRequest addRequest) {
        Booking booking = new Booking();

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
        this.saveBookingDate(addRequest, booking);

    }

    private void saveBookingDate(AddRequest addRequest, Booking booking) {
        List<Dates> datesList = new ArrayList<>();

        if(addRequest.isLongBooking()){
            this.bookingFirstDay(addRequest, booking, datesList);
            //長期預約
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
            this.bookingFirstDay(addRequest, booking, datesList);
            booking.setDateList(datesList);
        }
    }

    private void bookingFirstDay(AddRequest addRequest, Booking booking, List<Dates> datesList) {
        Dates date = new Dates();
        date.setDate(addRequest.getDate());
        date.setSartTime(addRequest.getStartTime());
        date.setEndTime(addRequest.getEndTime());
        date.setBooking(booking);
        datesList.add(date);
    }

    @Override
    @Transactional
    public void updateBooking(UpdateRequest updateRequest) {
        Booking booking = bookingRepository.getOne(updateRequest.getBookingId());
        Rooms rooms = roomsRepository.getOne(updateRequest.getRoom());
        booking.setRooms(rooms);
        booking.setTheme(updateRequest.getTheme());

        //相關人
        List<Users> usersList = new ArrayList<>();
        //建立人
        Users creator = usersRepository.getOne(updateRequest.getCreator());
        usersList.add(creator);
        //參加人
        for(Integer joinUserId : updateRequest.getJoinUser()){
            Users joinUser = usersRepository.getOne(joinUserId);
            usersList.add(joinUser);
        }
        booking.setJoinUser(usersList);

        //日期+時間
        this.saveBookingDate(updateRequest, booking);
        bookingRepository.save(booking);
    }

    private void saveBookingDate(UpdateRequest updateRequest, Booking booking){
        List<Dates> datesList = new ArrayList<>();
        Dates date = datesRepository.findByBooking(booking);
        date.setDate(updateRequest.getDate());
        date.setSartTime(updateRequest.getStartTime());
        date.setEndTime(updateRequest.getEndTime());
        date.setBooking(booking);
        datesList.add(date);
        booking.setDateList(datesList);
    }

    @Override
    @Transactional
    public void updateLongBooking(UpdateLongRequest updateLongRequest) {
        Booking booking = bookingRepository.getOne(updateLongRequest.getBookingId());
        Rooms rooms = roomsRepository.getOne(updateLongRequest.getRoom());
        booking.setRooms(rooms);
        booking.setTheme(updateLongRequest.getTheme());

        //相關人
        List<Users> usersList = new ArrayList<>();
        //建立人
        Users creator = usersRepository.getOne(updateLongRequest.getCreator());
        usersList.add(creator);
        //參加人
        for(Integer joinUserId : updateLongRequest.getJoinUser()){
            Users joinUser = usersRepository.getOne(joinUserId);
            usersList.add(joinUser);
        }
        booking.setJoinUser(usersList);

        //時間
        this.saveBookingTime(updateLongRequest, booking);
        bookingRepository.save(booking);
    }

    private void saveBookingTime(UpdateLongRequest updateLongRequest, Booking booking){
        List<Dates> datesList = datesRepository.findAllByBooking(booking);
        for(Dates date : datesList){
            date.setSartTime(updateLongRequest.getStartTime());
            date.setEndTime(updateLongRequest.getEndTime());
            date.setBooking(booking);
        }
        booking.setDateList(datesList);
    }

    @Override
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingDetailResponse getBookingDetail(int id) {
        Booking booking = bookingRepository.getOne(id);
        BookingDetailResponse response = new BookingDetailResponse();
        response.setRoomName(booking.getRooms().getName());
        response.setTheme(booking.getTheme());

        List<String> Users = new ArrayList<>();
        if(booking.getJoinUser().size() != 1){
            for(int i=1; i<booking.getJoinUser().size(); i++){
                Users.add(booking.getJoinUser().get(i).getName());
            }
        }
        response.setJoinUser(Users);
        response.setDate(booking.getDateList().get(0).getDate());
        response.setStartTime(booking.getDateList().get(0).getSartTime());
        response.setEndTime(booking.getDateList().get(0).getEndTime());
        return response;
    }
}
