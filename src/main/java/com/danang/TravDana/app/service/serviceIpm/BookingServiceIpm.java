package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.BookingEntity;
import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.exception.UserNotFoundException;
import com.danang.TravDana.app.repository.BookingRepo;
import com.danang.TravDana.app.repository.TourRepo;
import com.danang.TravDana.app.repository.UserRepo;
import com.danang.TravDana.app.request.BookingRequest;
import com.danang.TravDana.app.response.BookingResponse;
import com.danang.TravDana.app.service.BookingService;
import com.danang.TravDana.app.service.TourService;
import com.danang.TravDana.app.service.UserService;
import com.danang.TravDana.app.utils.SendMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceIpm implements BookingService {

    UserRepo userRepo;
    TourRepo tourRepo;
    SendMailService sendMailService;
    private final BookingRepo bookingRepo;

    @Transactional
    @Override
    public BookingResponse booking(BookingRequest bookingRequest, String tourId, String userName) {
        UserEntity user = userRepo
                .findByUserName(userName)
                .orElseThrow();

        TourEntity tour = tourRepo
                .findByTourID(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));


        int totalPrice = tour.getPrice() * bookingRequest.getAdultCount()
                + tour.getChildPrice() * bookingRequest.getChildCount();

        String contentPayment = userName + "_" + tourId + "_" + "DAT_TOUR";

        System.out.println(user);
        System.out.println(tour);

        BookingEntity booking = new BookingEntity();
        booking.setUser(user);
        booking.setTour(tour);
        booking.setCccd(bookingRequest.getCccd());
        booking.setTotalPrice(totalPrice);
        booking.setAmountPaid((int) (totalPrice * 0.5));
        booking.setPaymentStatus("CHO_XAC_NHAN");
        booking.setBookingDate(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        booking.setAdultCount(bookingRequest.getAdultCount());
        booking.setChildCount(bookingRequest.getChildCount());
        booking.setContentPayment(contentPayment);


        try {
            bookingRepo.save(booking);
            sendMailService.noficationBookingMail(user.getEmail()
                    , tourId
                    , tour.getTourName()
                    , user.getFullName()
                    , booking
                    , tour.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return BookingResponse.builder()
                .tourId(booking.getTour().getTourId())
                .tourName(booking.getTour().getTourName())
                .startDate(booking.getTour().getStartDate())
                .adult(booking.getAdultCount())
                .child(booking.getChildCount())
                .totalPrice(booking.getTotalPrice())
                .amount((int) (booking.getTotalPrice() * 0.3))
                .paymenStatus(booking.getPaymentStatus())
                .contentPayment(contentPayment)
                .build();
    }
}
