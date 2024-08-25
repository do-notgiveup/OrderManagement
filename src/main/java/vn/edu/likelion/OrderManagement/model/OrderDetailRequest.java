package vn.edu.likelion.OrderManagement.model;

import lombok.Data;

@Data
public class OrderDetailRequest {

    //OrderDetail Id
    private int Id;

    // id món ăn
    private int dishId;

    //Ten mon an
    private String dishName;

    //Hinh anh mon an
    private String image;

    // số lượng của từng món ăn được gọi
    private int quantity;

    // giá của từng món được gọi
    private double pricePerItem;

    // note của món ăn
    private String note;
}
