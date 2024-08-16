package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;

import java.util.List;

public interface OrderService extends BaseCRUD<OrderEntity> {
    public OrderEntity createOrder(OrderEntity order, List<OrderDetailEntity> orderDetails);

}
