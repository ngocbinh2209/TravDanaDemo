package com.danang.TravDana.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image_site")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ImageTourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private SiteEntity site;

    @Override
    public String toString() {
        return "ImageTourEntity" +
                " [imageId=" + imageId + "," +
                " imageUrl=" + imageUrl + "]";
    }
}
