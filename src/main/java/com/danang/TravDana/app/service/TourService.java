package com.danang.TravDana.app.service;

import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.response.TourResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface TourService {


//    List<TourResponse> getAllTour();

    Page<TourResponse> getAllTourSale(int page, int sale);

    Page<TourResponse> getAllTours(int page);

    TourResponse getTourInformation(String name) throws IllegalAccessException;

    TourResponse getTourInformationForBokking(String name) throws IllegalAccessException;

    List<TourEntity> getall();

    List<TourEntity> getAvailable();



}
