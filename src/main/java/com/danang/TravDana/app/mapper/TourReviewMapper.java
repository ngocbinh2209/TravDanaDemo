package com.danang.TravDana.app.mapper;


import com.danang.TravDana.app.entity.TourReviewEntity;
import com.danang.TravDana.app.response.TourReviewResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TourReviewMapper {
    TourReviewResponse toTourReviewResponse(TourReviewEntity tourReviewEntity);
}
