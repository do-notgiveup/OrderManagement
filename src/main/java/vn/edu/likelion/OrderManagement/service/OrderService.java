package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.model.OrderRequest;

import java.util.List;

import java.util.List;

public interface OrderService extends BaseCRUD<OrderEntity> {

    OrderEntity createOrder(OrderRequest orderRequest);
}
