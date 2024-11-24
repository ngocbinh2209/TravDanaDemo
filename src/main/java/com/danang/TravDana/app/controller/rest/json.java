package com.danang.TravDana.app.controller.rest;

import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.entity.TourReviewEntity;
import com.danang.TravDana.app.response.TourResponse;
import com.danang.TravDana.app.service.TourReviewService;
import com.danang.TravDana.app.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class json {
    private final TourService tourService;
    private final TourReviewService tourReviewService;

    @GetMapping("/tourDetail/all")
    public ResponseEntity tourDetails(@RequestParam("tourId") String tourId) throws IllegalAccessException {

        return ResponseEntity.ok().body(tourService.getTourInformation(tourId));
    }
    @GetMapping("/tourDetail/test2")
    public ResponseEntity<TourResponse> tourDetails2(@RequestParam("tourId") String tourId) throws IllegalAccessException {

        return ResponseEntity.ok().body(tourService.getTourInformation(tourId));
    }

    @GetMapping("/test")
    public ResponseEntity<List<TourEntity>> test() {

        return ResponseEntity.ok().body(tourService.getall());
    }
    
    @GetMapping("/GetComment")
    public ResponseEntity<List<TourReviewEntity>> GetComment(@RequestParam("tourId") String tourId) {
        return ResponseEntity.ok().body(tourReviewService.getImageByTourID(tourId));
    }
}
