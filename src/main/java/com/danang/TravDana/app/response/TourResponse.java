package com.danang.TravDana.app.response;


import com.danang.TravDana.app.entity.ImageTourEntity;
import com.danang.TravDana.app.entity.ScheduleEntity;
import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.entity.TourReviewEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class TourResponse {

    public String tourId;
    String tourName;
    String description;
    LocalDate startDate;
    String numberDay;
    int price;
    int childPrice;
    String tourStatus;
    int minCapacity;
    int sale;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String images;

    List<ScheduleEntity> schedules;
    List<TourReviewEntity> tourReviews;

}