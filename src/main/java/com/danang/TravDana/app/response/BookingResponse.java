package com.danang.TravDana.app.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
public class BookingResponse {

    public String tourId;
    public String tourName;
    public LocalDate startDate;
    public int adult;
    public int child;
    public int totalPrice;
    public int amount;
    public String paymenStatus;
    public String contentPayment;

}
