package com.danang.TravDana.app.repository;

import com.danang.TravDana.app.entity.ImageSiteEntity;
import com.danang.TravDana.app.entity.ImageTourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageSiteRepo extends JpaRepository<ImageSiteEntity, Integer> {

}
