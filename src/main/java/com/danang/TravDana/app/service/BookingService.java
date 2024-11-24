package com.danang.TravDana.app.service;


import com.danang.TravDana.app.request.BookingRequest;
import com.danang.TravDana.app.response.BookingResponse;

public interface BookingService {

    BookingResponse booking(BookingRequest bookingRequest, String tourId, String userName);
}
