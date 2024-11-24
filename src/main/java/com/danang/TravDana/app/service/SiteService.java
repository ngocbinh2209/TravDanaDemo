package com.danang.TravDana.app.service;

import com.danang.TravDana.app.entity.SiteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SiteService {
    List<SiteEntity> findAll();

    Page<SiteEntity> findAll(Pageable pageable);

//    Page<SiteEntity> findBySiteId(int cid, Pageable pageable);

    SiteEntity findById(Integer id);

    SiteEntity save(SiteEntity site);


    Page<SiteEntity> getSiteByIdWithImages(int cid, Pageable pageable);
}
