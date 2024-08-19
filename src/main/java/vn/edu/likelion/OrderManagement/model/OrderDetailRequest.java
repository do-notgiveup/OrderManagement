package vn.edu.likelion.OrderManagement.model;

import lombok.Data;

@Data
public class OrderDetailRequest {

    // id món ăn
    private int dishId;

    // số lượng của từng món ăn được gọi
    private int quantity;

    // giá của từng món được gọi
    private double pricePerItem;

    // note của món ăn
    private String note;
}
