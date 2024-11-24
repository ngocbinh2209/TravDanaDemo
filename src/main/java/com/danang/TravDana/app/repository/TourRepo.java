package com.danang.TravDana.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.danang.TravDana.app.entity.TourEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TourRepo extends JpaRepository<TourEntity, Integer> {

    @Query(value = "select * from tour where sale= :sale", nativeQuery = true)
    Page<TourEntity> findBySale(int sale, Pageable pageable);

    @Query(value = "select * from tour where tour_id= :tourID", nativeQuery = true)
    Optional<TourEntity> findByTourID(String tourID);

    @Query(value = "select * from tour where tour_status= ?", nativeQuery = true)
    List<TourEntity> findByStatus(String status);


}
