package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.model.OrderRequest;

import java.util.List;

import java.util.Optional;

public interface OrderService extends BaseCRUD<OrderEntity> {

    OrderEntity createOrder(OrderRequest orderRequest);

    String payOrder(OrderRequest orderRequest);

    List<OrderRequest> getAllOrder();

    Optional<OrderRequest> findOrderById(int orderId);

    OrderRequest findOrderByTable(int tableId);
}
