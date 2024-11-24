package com.danang.TravDana.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Site")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "schedules")
public class SiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "district")
    private String district;

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ImageSiteEntity> images;

    @OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ScheduleEntity> schedules;

    @Override
    public String toString() {
        return "SiteEntity{" +
                "id=" + id +
                ", scheduleCount=" + (schedules != null ? schedules.size() : 0) +
                '}';
    }
}
