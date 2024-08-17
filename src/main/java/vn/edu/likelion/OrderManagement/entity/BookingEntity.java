package vn.edu.likelion.OrderManagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_booking")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // tên của khách hàng điền form đặt bàn
    @Column
    private String name;

    // địa chỉ của khách hàng đó
    @Column
    private String address;

    // đặt bàn vào ngày nào là lúc mấy giờ
    @Column
    private LocalDateTime bookingDate;

    // sđt của khách hàng đặt bàn
    @Column
    private String phoneNumber;

    // đặt bàn bao nhiêu người
    @Column
    private int seat;

    // trạng thái của form đặt bàn (0: pending, 1: approved, 2: reject)
    @Column
    private int status = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private UserEntity user;

}
