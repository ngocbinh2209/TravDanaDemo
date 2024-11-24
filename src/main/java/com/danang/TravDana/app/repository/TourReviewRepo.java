package com.danang.TravDana.app.repository;

import com.danang.TravDana.app.entity.TourReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourReviewRepo extends JpaRepository<TourReviewEntity, Integer>{

    @Query(value = "select * from tour_review " +
            "where tour_id = :tourID", nativeQuery = true)
    List<TourReviewEntity> findReviewByTourID(@Param("tourID") String tourID);

}





