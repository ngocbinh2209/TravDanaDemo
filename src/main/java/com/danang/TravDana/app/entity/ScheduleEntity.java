package com.danang.TravDana.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "tour")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "tour_id", referencedColumnName = "tour_id")
    private TourEntity tour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false)
    private SiteEntity site;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "tour_guide")
    private String tourGuide;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;


    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", siteId=" + (site != null ? site.getId() : null) +
                ", site=" + (site != null ? site.getName() : "null") +
                ", tour=" + (tour != null ? tour.getTourName() : "null") +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description=" + description +
                '}';
    }

}
