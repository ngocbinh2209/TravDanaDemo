package com.danang.TravDana.app.controller.site;


import com.danang.TravDana.app.entity.SiteEntity;
import com.danang.TravDana.app.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/site")
public class SiteRestController {
    @Autowired
    SiteService siteService;

    @GetMapping()
    public List<SiteEntity> getAll() {
        return siteService.findAll();
    }

    @GetMapping("{id}")
    public SiteEntity getOne(@PathVariable("id") Integer id) {

        return siteService.findById(id);
    }


}
