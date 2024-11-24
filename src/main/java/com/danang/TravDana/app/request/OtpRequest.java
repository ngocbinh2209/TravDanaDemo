package com.danang.TravDana.app.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OtpRequest {
    private String email;
    private String otp;

}
