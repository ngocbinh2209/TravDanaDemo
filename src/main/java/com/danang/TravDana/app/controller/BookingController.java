package com.danang.TravDana.app.controller;

import com.danang.TravDana.app.request.BookingRequest;
import com.danang.TravDana.app.response.BookingResponse;
import com.danang.TravDana.app.service.BookingService;
import com.danang.TravDana.app.service.TourService;
import com.danang.TravDana.app.service.UserService;
import com.danang.TravDana.auth.SessionRetrievalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class BookingController {

    TourService tourService;
    SessionRetrievalService sessionRetrievalService;
    UserService userService;
    BookingService bookingService;


    @GetMapping("/bookingPage")
    public String bookingPage(Model model,
                              @RequestParam("tourId") String tourId,
                              HttpServletRequest request

    ) throws IllegalAccessException {

        model.addAttribute("user",
                userService
                        .getUserBooking(
                                sessionRetrievalService
                                        .getUserFromSession(request)
                                        .getUsername())
        );
        model.addAttribute("bookingRequest", new BookingRequest());
        model.addAttribute("tour", tourService.getTourInformationForBokking(tourId));
        return "view/datTour";
    }
    @PostMapping("/submitBooking")
    public String submitBooking(@ModelAttribute("bookingRequest") BookingRequest bookingRequest,
            @RequestParam("tourId") String tourId,
            HttpServletRequest request,
            Model model) {
        BookingResponse booking = bookingService.booking(bookingRequest, tourId,
                sessionRetrievalService.getUserFromSession(request).getUsername());
        if(booking != null) {
            model.addAttribute("booking", booking);
            return "view/submitBooking";
        }else{
            model.addAttribute("booking", booking);
            return "view/submitBooking";
//            model.addAttribute("tourId", tourId);
//            model.addAttribute("message", "Vui lòng kiểm tra thông tin");
//            return "view/datTour";
        }

    }


}
