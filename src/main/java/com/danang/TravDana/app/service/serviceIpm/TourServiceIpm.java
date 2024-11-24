package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.repository.*;
import com.danang.TravDana.app.response.TourResponse;
import com.danang.TravDana.app.service.TourService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TourServiceIpm implements TourService {

    private final TourRepo tourRepo; // Autowired by constructor


    @Override
    public Page<TourResponse> getAllTourSale(int page, int sale) {
        Pageable pageable = PageRequest.of(page, 6);
        log.info("BookingEntity: {}", tourRepo.findAll(pageable));

        if (sale == 0) {
            return tourRepo.findAll(pageable).map(tour ->TourResponse.builder()
                    .tourId(tour.getTourId())
                    .tourName(tour.getTourName())
                    .description(tour.getDescription())
                    .startDate(tour.getStartDate())
                    .numberDay(tour.getNumberDay())
                    .price(tour.getPrice())
                    .childPrice(tour.getChildPrice())
                    .tourStatus(tour.getTourStatus())
                    .minCapacity(tour.getMinCapacity())
                    .sale(tour.getSale())
                    .createdAt(tour.getCreatedAt())
                    .updatedAt(tour.getUpdatedAt())
                    .images(tour.getImages())
                    .build());
        } else {
            return tourRepo.findBySale(sale, pageable).map(tour -> TourResponse.builder()
                    .tourId(tour.getTourId())
                    .tourName(tour.getTourName())
                    .description(tour.getDescription())
                    .startDate(tour.getStartDate())
                    .numberDay(tour.getNumberDay())
                    .price(tour.getPrice())
                    .childPrice(tour.getChildPrice())
                    .tourStatus(tour.getTourStatus())
                    .minCapacity(tour.getMinCapacity())
                    .sale(tour.getSale())
                    .createdAt(tour.getCreatedAt())
                    .updatedAt(tour.getUpdatedAt())
                    .images(tour.getImages())
                    .build());
        }
    }
    @Override
    public Page<TourResponse> getAllTours(int page) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<TourEntity> tourPage = tourRepo.findAll(pageable);
        return tourPage.map(tour ->TourResponse.builder()
                .tourId(tour.getTourId())
                .tourName(tour.getTourName())
                .description(tour.getDescription())
                .startDate(tour.getStartDate())
                .numberDay(tour.getNumberDay())
                .price(tour.getPrice())
                .childPrice(tour.getChildPrice())
                .tourStatus(tour.getTourStatus())
                .minCapacity(tour.getMinCapacity())
                .sale(tour.getSale())
                .createdAt(tour.getCreatedAt())
                .updatedAt(tour.getUpdatedAt())
                .images(tour.getImages())
                .build());
    }


    @Override
    public TourResponse getTourInformation(String tourId) throws IllegalAccessException {
        TourEntity tourEntity = tourRepo
                .findByTourID(tourId)
                .orElseThrow(IllegalAccessException::new);

        return TourResponse.builder()
                .tourId(tourEntity.getTourId())
                .tourName(tourEntity.getTourName())
                .description(tourEntity.getDescription())
                .startDate(tourEntity.getStartDate())
                .numberDay(tourEntity.getNumberDay())
                .price(tourEntity.getPrice())
                .childPrice(tourEntity.getChildPrice())
                .tourStatus(tourEntity.getTourStatus())
                .minCapacity(tourEntity.getMinCapacity())
                .sale(tourEntity.getSale())
                .createdAt(tourEntity.getCreatedAt())
                .updatedAt(tourEntity.getUpdatedAt())
                .images(tourEntity.getImages())
                .schedules(tourEntity.getSchedules())
                .tourReviews(tourEntity.getTourReviewEntityList())
                .build();
    }

    @Override
    public TourResponse getTourInformationForBokking(String tourId) throws IllegalAccessException {
        TourEntity tourEntity = tourRepo.findByTourID(tourId).orElseThrow();

        return TourResponse.builder()
                .tourId(tourEntity.getTourId())
                .tourName(tourEntity.getTourName())
                .description(tourEntity.getDescription())
                .startDate(tourEntity.getStartDate())
                .numberDay(tourEntity.getNumberDay())
                .price(tourEntity.getPrice())
                .childPrice(tourEntity.getChildPrice())
                .tourStatus(tourEntity.getTourStatus())
                .minCapacity(tourEntity.getMinCapacity())
                .sale(tourEntity.getSale())
                .createdAt(tourEntity.getCreatedAt())
                .updatedAt(tourEntity.getUpdatedAt())
                .images(tourEntity.getImages())
                .schedules(tourEntity.getSchedules())
                .build();
    }

    @Override
    public List<TourEntity> getall(){
        return tourRepo.findAll();
    }
    @Override
    public List<TourEntity> getAvailable() { return tourRepo.findByStatus("Available"); }

}
