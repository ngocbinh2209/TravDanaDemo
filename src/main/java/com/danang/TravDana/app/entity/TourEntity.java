package com.danang.TravDana.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tour")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourEntity {

    @Id
    @Column(name = "tour_id")
    private String tourId;

    @Column(name = "tour_name", nullable = false)
    private String tourName;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "number_day")
    private String numberDay;

    @Column(name = "price")
    private int price;

    @Column(name = "child_price")
    private int childPrice;

    @Column(name = "tour_status")
    private String tourStatus;

    @Column(name = "min_capacity")
    private int minCapacity;

    @Column(name = "sale")
    private int sale;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "images")
    private String images;

    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BookingEntity> bookings;


    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<ScheduleEntity> schedules;


    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<TourReviewEntity> tourReviewEntityList;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Tour tourId:" + tourId + "TourName" + tourName;
    }
}
