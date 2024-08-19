package vn.edu.likelion.OrderManagement.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDTO {
    private int id;
    private int orderId;
    private LocalDate invoiceDate;
    private double totalAmount;
}
