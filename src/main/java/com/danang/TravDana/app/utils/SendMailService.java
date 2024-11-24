package com.danang.TravDana.app.utils;


import com.danang.TravDana.app.entity.BookingEntity;
import com.danang.TravDana.app.entity.TourEntity;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SendMailService {
    JavaMailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("ntr22092300@gmail.com");

            mailSender.send(message);
            System.out.println("Email sent : " + to);

        } catch (MailException e) {
            throw new MailSendException("Error sending email");
        } catch (Exception e) {
            throw e;
        }

    }

    public void sendOTPtoUser(String email, String OTP){
        String content = "Vui lòng không chia sẽ mã OTP này.\n" +
                "\n" +
                "Mã OTP: "+OTP+"\n"+
                "Mã OTP này chỉ có hiệu lực trong vòng 60 giây. Nếu bạn không yêu cầu mã OTP, xin vui lòng bỏ qua email này.";
        this.sendEmail(email, "Mã Xác Nhận", content);
    }

    public void noficationBookingMail(String email, String tourId, String tourName, String fullName, BookingEntity booking, String startDate) {
        String contentMail = "Kính gửi "+fullName+",\n" +
                "\n" +
                "Cảm ơn bạn đã tin tưởng và lựa chọn dịch vụ của chúng tôi. Dưới đây là thông tin về đặt tour của bạn:\n" +
                "\n" +
                "Tên tour: "+tourName+"\n" +
                "Mã tour: "+tourId+"\n" +
                "Ngày khởi hành: "+startDate+"\n" +
                "Người lớn: "+booking.getAdultCount()+"\n" +
                "Trẻ em: "+booking.getChildCount()+"\n"+
                "Tổng chi phí: "+booking.getTotalPrice()+"\n" +
                "Số tiền đã thanh toán: "+booking.getAmountPaid()+"\n" +
                "Tình trạng thanh toán: "+booking.getPaymentStatus()+"\n" +
                "\nNếu bạn có bất kỳ thắc mắc nào, vui lòng liên hệ với chúng tôi qua email hoặc số điện thoại hỗ trợ.\n" +
                "\nMột lần nữa, cảm ơn bạn đã đồng hành cùng chúng tôi.\n" +
                "\n" +
                "Trân trọng,\n" +
                "Travdana\n" +
                "Phone: 0778589863\n"+
                "Address: 30 Võ Nguyên Giáp, Ngũ Hành Sơn, Đà Nẵng.";


        this.sendEmail(email
                , "THÔNG BÁO ĐẶT TOUR - " + tourName.toUpperCase(),
                contentMail
        );

    }
}
