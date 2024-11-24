package com.danang.TravDana.app.controller;

import com.danang.TravDana.app.entity.TourReviewEntity;
import com.danang.TravDana.app.entity.UserEntity;
import com.danang.TravDana.app.service.TourReviewService;
import com.danang.TravDana.app.service.TourService;
import com.danang.TravDana.app.service.UserService;
import com.danang.TravDana.app.service.serviceIpm.TourServiceIpm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.danang.TravDana.app.entity.ScheduleEntity;
import com.danang.TravDana.app.entity.TourEntity;
import com.danang.TravDana.app.service.ScheduleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping ("/fetch")
public class FetchDisplayController {

    private final TourServiceIpm tourServiceIpm;

    private final UserService userService;

    private final TourReviewService tourReviewService;
    public FetchDisplayController(TourServiceIpm tourServiceIpm, UserService userService, TourReviewService tourReviewService) {
        this.tourServiceIpm = tourServiceIpm;
        this.userService = userService;
        this.tourReviewService = tourReviewService;
    }

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    TourService tourService;

    @GetMapping("/thongTinCaNhan")
    public ModelAndView thongTinCaNhan(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/thongTinCaNhan");

        // Lấy userId từ session
        Integer userId = (Integer) session.getAttribute("userId");

        // Kiểm tra nếu userId có trong session
        if (userId != null) {
            // Sử dụng service để tìm thông tin người dùng theo userId
            UserEntity userEntity = userService.findUserById(userId);  // Dùng findUserById của UserService

            if (userEntity != null) {
                // Truyền thông tin người dùng vào ModelAndView
                modelAndView.addObject("userId", userEntity.getUserId());
                modelAndView.addObject("fullName", userEntity.getFullName());
                modelAndView.addObject("phone", userEntity.getPhone());
                modelAndView.addObject("email", userEntity.getEmail());
                modelAndView.addObject("birthday", userEntity.getBirthday()); // sửa: kiểm tra ngày tháng
                modelAndView.addObject("gender", userEntity.isGender() ? "Nam" : "Nữ");
                modelAndView.addObject("createdAt", userEntity.getCreatedAt()); // sửa: kiểm tra ngày tháng
            } else {
                // Trường hợp không tìm thấy người dùng
                modelAndView.addObject("error", "Không tìm thấy thông tin người dùng.");
            }
        } else {
            modelAndView.addObject("error", "Phiên đăng nhập không hợp lệ.");
        }

        return modelAndView;
    }

//    @PostMapping("test"){
//
//    }

    @GetMapping("/doiMatKhau")
    public ModelAndView doiMatKhau() {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/doiMatKhau");
        return modelAndView;
    }
    @GetMapping("/dangKiTaiKhoan")
    public ModelAndView dangKiTaiKhoan() {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/dangKiTaiKhoan");
        return modelAndView;
    }
    @GetMapping("/qlNhanVien")
    public ModelAndView qlNhanVien() {
        List<UserEntity> employees = userService.getEmployeesByRole(2);
        int totalEmployees = employees.size();


        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/qlNhanVien");
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("totalEmployees", totalEmployees);
        UserEntity sdf = new UserEntity();
        sdf.setUserName("binh bo");

        modelAndView.addObject("user32", new UserEntity());
        return modelAndView;
    }
    @PostMapping("/qlNhanVien2")
        public ModelAndView qlNhanVienPost(@RequestBody UserEntity a ) {
        System.out.println(a);
        // luuw db
        return qlNhanVien();
    }
    @GetMapping("/qlHuongDanVien")
    public ModelAndView qlHuongDanVien() {
        List<UserEntity> employees = userService.getEmployeesByRole(4);
        int totalEmployees = employees.size();

        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/qlHuongDanVien");
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("totalEmployees", totalEmployees);
        return modelAndView;
    }
    @GetMapping("/qlKhachHang")
    public ModelAndView qlKhachHang() {
        List<UserEntity> employees = userService.getEmployeesByRole(3);
        int totalEmployees = employees.size();

        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/qlKhachHang");
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("totalEmployees", totalEmployees);
        return modelAndView;
    }
    @GetMapping("/themTourMoi")
    public ModelAndView themTourMoi() {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/themTourMoi");
        return modelAndView;
    }
    @GetMapping("/tourDangHoatDong")
    public ModelAndView tourDangHoatDong(Model model) {
        List<TourEntity> availableTours = tourService.getAvailable();
        model.addAttribute("toursAvailable", availableTours);
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/tourDangHoatDong");
        // modelAndView.addObject("toursAvailable", tourService.getAvailable() );
        modelAndView.addObject("totalTours", tourService.getall().size());
        return modelAndView;
    }
    @GetMapping("/tatCaTour")
    public ModelAndView tatCaTour() {

        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/tatCaTour");
        modelAndView.addObject("Tours", tourService.getall() );
        modelAndView.addObject("totalTours", tourService.getall().size());
        return modelAndView;
    }
    @GetMapping("/trangThaiThanhToan")
    public ModelAndView trangThaiThanhToanTour() {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/trangThaiThanhToan");
        return modelAndView;
    }
    @GetMapping("/qlDoanhThu")
    public ModelAndView qlDoanhThu() {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/qlDoanhThu");
        return modelAndView;
    }
    @GetMapping("/thongKeTour")
    public ModelAndView thongKeTour() {
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/thongKeTour");
        return modelAndView;
    }
    @GetMapping("/danhGiaTour")
    public ModelAndView danhGiaTour( Model model) {
        List<TourReviewEntity> reviews = tourReviewService.findAll();
        model.addAttribute("reviews",reviews );
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/danhGiaTour");
        return modelAndView;
    }
    @GetMapping("/xemLichTrinhCongViec")
    public ModelAndView xemLichTrinhCongViec(Model model) {
        List<ScheduleEntity> ls = scheduleService.findAll();
        model.addAttribute("LCV" ,ls);
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/xemLichTrinhCongViec");
        return modelAndView;
    }


    @GetMapping("/xemThongTinTour")
    public ModelAndView xemThongTinTour( Model model) {
        List<TourEntity>  ls = tourService.getall();
        model.addAttribute("TTTour", ls);
        ModelAndView modelAndView = new ModelAndView("view/manage_TravDana/xemThongTinTour");
        return modelAndView;
    }
}


