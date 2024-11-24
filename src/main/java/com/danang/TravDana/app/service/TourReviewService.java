package com.danang.TravDana.app.service;

import com.danang.TravDana.app.entity.ScheduleEntity;
import com.danang.TravDana.app.entity.TourReviewEntity;
import com.danang.TravDana.app.repository.TourReviewRepo;
import com.danang.TravDana.app.response.TourReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface TourReviewService {
    List<TourReviewEntity> findAll();
    TourReviewEntity findByTourReviewId(int tourReviewId);
    List<TourReviewEntity>     getImageByTourID(String tourID);

}
