package com.danang.TravDana.app.controller.site;

import com.danang.TravDana.app.entity.SiteEntity;
import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.service.SiteService;
import com.danang.TravDana.app.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class SiteController {
    @Autowired
    SiteService siteService;

    @Autowired
    TourService tourService;


    @RequestMapping("/diaDiem")
    public String list(Model model, @RequestParam("cid") Optional<Integer> cid, Pageable pageable) {

        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), 6);

        Page<SiteEntity> page;
        if (cid.isPresent()) {
            page = siteService.getSiteByIdWithImages(cid.get(), pageRequest);
        } else {
            page = siteService.findAll(pageRequest);
        }

        model.addAttribute("item", page.getContent());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        return "view/diaDiemThamQuan";
    }


    @RequestMapping("/chiTietThamQuan/{id}")
    public String ChiTietSite(Model model, @PathVariable("id") Integer id) {
        SiteEntity item = siteService.findById(id);
        model.addAttribute("item", item);

       List<TourEntity>  ls = tourService.getall();
       model.addAttribute("tourList", ls);
        return "view/detail_component/chiTietThamQuan";
    }



}
