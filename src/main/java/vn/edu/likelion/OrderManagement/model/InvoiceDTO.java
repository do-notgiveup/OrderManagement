package vn.edu.likelion.OrderManagement.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceDTO {
    private int id;
    private int orderId;
    private LocalDateTime invoiceDate;
    private double totalAmount;
}
