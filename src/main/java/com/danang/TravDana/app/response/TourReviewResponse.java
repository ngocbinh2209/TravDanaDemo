package com.danang.TravDana.app.response;

import com.danang.TravDana.app.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TourReviewResponse {

    private int rating;

    private String comment;

    private UserEntity user;

}
