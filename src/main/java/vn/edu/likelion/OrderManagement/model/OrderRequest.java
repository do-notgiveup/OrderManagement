package vn.edu.likelion.OrderManagement.model;

import lombok.Data;
import vn.edu.likelion.OrderManagement.entity.DishEntity;

import java.util.List;

@Data
public class OrderRequest {

    private int orderId;

    //giá của cả tổng bill
    private double totalPrice;

    // ban đầu chỉ mới gọi món chưa thanh toán thì mặc định sẽ là false
//    private boolean status;

    // id của table, order này của bàn nào
    private int tableId;

    // id của user là người tạo order, ở đây là staff
    private int userId;

    // danh sách các món ăn được order trong bàn, trong 1 phiên order
    List<OrderDetailRequest> orderDetailRequests;

}
