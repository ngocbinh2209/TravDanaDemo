package com.danang.TravDana.app.service.serviceIpm;

import com.danang.TravDana.app.entity.ImageSiteEntity;
import com.danang.TravDana.app.entity.SiteEntity;
import com.danang.TravDana.app.repository.SiteRepo;
import com.danang.TravDana.app.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceIpm  implements SiteService {
    @Autowired
    SiteRepo siteRepo;



    @Override
    public List<SiteEntity> findAll() {
        return siteRepo.findAll();
    }


    @Override
    public Page<SiteEntity> findAll(Pageable pageable) {
        return siteRepo.findAll(pageable);
    }



    @Override
    public SiteEntity findById(Integer id) {
        return siteRepo.findById(id).get();
    }



    @Override
    public SiteEntity save(SiteEntity site) {
        return siteRepo.save(site);
    }


    @Override
    public Page<SiteEntity> getSiteByIdWithImages(int cid, Pageable pageable) {
        return siteRepo.findByIdWithImages(cid, pageable);
    }



}