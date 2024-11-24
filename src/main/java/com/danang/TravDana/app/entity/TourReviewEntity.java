package com.danang.TravDana.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tour_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "tour")
public class TourReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_review_id")
    private int tourReviewId;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "tour_id",referencedColumnName = "tour_id")
    private TourEntity tour;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
