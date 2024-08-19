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
@Table(name = "tbl_invoice")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // ngày của invoice
    @Column
    private LocalDateTime invoiceDate;

    // tổng số lượng bao nhiêu
    @Column
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

}
