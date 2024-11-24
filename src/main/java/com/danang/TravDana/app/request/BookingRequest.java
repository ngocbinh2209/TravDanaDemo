package com.danang.TravDana.app.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingRequest {

    private String cccd;
    private int adultCount;
    private int childCount;


}
