package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;
import vn.edu.likelion.OrderManagement.repository.OrderDetailRepository;
import vn.edu.likelion.OrderManagement.repository.OrderRepository;
import vn.edu.likelion.OrderManagement.service.OrderService;

import java.util.List;
import java.util.Optional;

/*
 * OrderManager - OrderServiceImpl
 * Author: Rains
 * Date: 15/8/2024
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderEntity create(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
        if (orderRepository.existsById(orderEntity.getId())) {
            return orderRepository.save(orderEntity);
        } else {
            throw new RuntimeException("Order not found with id: " + orderEntity.getId());
        }
    }

    @Override
    public boolean delete(OrderEntity orderEntity) {
        if (orderRepository.existsById(orderEntity.getId())) {
            orderRepository.delete(orderEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> findById(int id) {
        return orderRepository.findById(id);
    }

    public OrderEntity createOrder(OrderEntity order, List<OrderDetailEntity> orderDetails) {
        OrderEntity savedOrder = orderRepository.save(order);
        for (OrderDetailEntity orderDetail : orderDetails) {
            orderDetail.setOrder(savedOrder);
            orderDetailRepository.save(orderDetail);
        }
        return savedOrder;
    }
}