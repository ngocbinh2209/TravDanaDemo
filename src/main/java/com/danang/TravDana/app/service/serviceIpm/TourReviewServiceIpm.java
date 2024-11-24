package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.ScheduleEntity;
import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.entity.TourReviewEntity;
import com.danang.TravDana.app.repository.TourReviewRepo;
import com.danang.TravDana.app.service.TourReviewService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourReviewServiceIpm implements TourReviewService {
    TourReviewRepo tourReviewRepo;

    @Override
    public List<TourReviewEntity> findAll() {
        return tourReviewRepo.findAll();
    }
    @Override
    public TourReviewEntity findByTourReviewId(int tourReviewId) {
        return tourReviewRepo.findById(tourReviewId).get();
    }
    @Override
    public List<TourReviewEntity> getImageByTourID(String tourID) {
        return tourReviewRepo.findReviewByTourID(tourID);
    }

}
