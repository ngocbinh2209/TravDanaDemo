package com.danang.TravDana.app.mapper;

import com.danang.TravDana.app.entity.ImageTourEntity;
import com.danang.TravDana.app.entity.ScheduleEntity;
import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.entity.TourReviewEntity;
import com.danang.TravDana.app.response.ImageTourResponse;
import com.danang.TravDana.app.response.ScheduleResponse;
import com.danang.TravDana.app.response.TourResponse;
import com.danang.TravDana.app.response.TourReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TourMapper {


    @Mapping(source = "schedules", target = "schedules")
    @Mapping(source = "tourReviewEntityList", target = "tourReviews")
    TourResponse toTourResponse(TourEntity tourEntity);

    ScheduleResponse toScheduleResponse(ScheduleEntity scheduleEntity);

    TourReviewResponse toTourReviewResponse(TourReviewEntity tourReviewEntity);

    List<ScheduleResponse> toScheduleResponseList(List<ScheduleEntity> schedules);

    List<TourReviewResponse> toTourReviewResponseList(List<TourReviewEntity> tourReviews);

}
