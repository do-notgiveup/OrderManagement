package vn.edu.likelion.OrderManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private int id;
    private String customerName;
    private String customerAddress;
    private LocalDateTime bookingTime;
    private String phoneNumber;
    private int seat;
    private int status;
    private int userId;
}
