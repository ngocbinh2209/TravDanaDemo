package com.danang.TravDana.app.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class DisplayController {

    @GetMapping("/")
    public String index() {

        return "view/index";
    }



    @GetMapping("/lienHe")
    public String lienHe() {

        return "view/lienHe";
    }

    @GetMapping("/khachSan")
    public String khachSan() {

        return "view/khachSan";
    }

    @GetMapping("/chiTietDiaDiem")
    public String chiTietDiaDiem() {

        return "view/detail_component/chiTietDiaDiem";
    }

    @GetMapping("/datTour")
    public String datTour() {

        return "view/detail_component/datTour";
    }
    @GetMapping("/thuVienAnh")
    public String thuVienAnh() {

        return "view/detail_component/thuVienAnh";
    }

    @GetMapping("/ttKhachHang")
    public String ttKhachHang() {

        return "view/thongTinKhachHang";
    }

    @GetMapping("/indexManage")
    public String admin() {

        System.out.println("Current Authentication after setting: " + SecurityContextHolder.getContext().getAuthentication());

        return "view/manage_TravDana/indexManage";
    }

}


