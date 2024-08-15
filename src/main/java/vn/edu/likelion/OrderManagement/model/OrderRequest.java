package vn.edu.likelion.OrderManagement.model;

import lombok.Data;

@Data
public class OrderRequest {

    //giá của cả tổng bill
    private double price;

    // ban đầu chỉ mới gọi món chưa thanh toán thì mặc định sẽ là false
//    private boolean status;

    // id của table, order này của bàn nào
    private int tableId;

    // id của user là người tạo order, ở đây là staff
    private int userId;

}
