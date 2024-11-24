package com.danang.TravDana.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TourDTO {
    //tour
    private int id;
    private String tourName;
    private Date createDate;

    //tourDetails
    private Date startDay;
    private Date endDay;
    private int smallerFivePrice;
    private int fiveToThirteenPrice;
    private int quantityLimit;
    private String description;
    private String transportation;
    private int newPrice;
    private int oldPrice;
    private int sale;

    //Hotel
    private String hotelId;
    private String hotelName;
    private double hotelRate;
    private String hotelDescription;
    private String hotelLink;

    //restaurant
    private String restaurantId;
    private String restaurantName;
    private String restaurantDescription;
    private String restaurantLink;
}
