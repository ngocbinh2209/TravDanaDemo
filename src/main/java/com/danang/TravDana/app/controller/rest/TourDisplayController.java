package com.danang.TravDana.app.controller.rest;

import com.danang.TravDana.app.response.TourResponse;
import com.danang.TravDana.app.service.TourReviewService;
import com.danang.TravDana.app.service.TourService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourDisplayController {

    TourService tourService;
//
//    @GetMapping("/tourList")
//    public String tourList(Model model) {
//
//        model.addAttribute("toursale", this.tourService.getAllTour());
//        model.addAttribute("tours",  this.tourService.getAllTour());
//        return "view/khachSan";
//    }

    @GetMapping("/tourList")
    public String tourList(@RequestParam(defaultValue = "0") int pageSale,
                           @RequestParam(defaultValue = "0") int pageTour,
                           @RequestParam(defaultValue = "0") int sale,
                           Model model) {

        Page<TourResponse> toursale = tourService.getAllTourSale(pageSale, sale);
        Page<TourResponse> tours = tourService.getAllTours(pageTour);

        model.addAttribute("toursale", toursale.getContent());
        model.addAttribute("totalPagesSale", toursale.getTotalPages());
        model.addAttribute("currentPageSale", pageSale);

        model.addAttribute("tours", tours.getContent());
        model.addAttribute("totalPagesTour", tours.getTotalPages());
        model.addAttribute("currentPageTour", pageTour);

        return "view/khachSan";
    }


    @GetMapping("/tourDetail")
    public String tourDetails(Model model,
                              @RequestParam("tourId") String tourId
    ) throws IllegalAccessException {
        model.addAttribute("tour", tourService.getTourInformation(tourId));
        return "view/detail_component/chiTietDiaDiem";
    }
}

