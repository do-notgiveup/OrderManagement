package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;
import vn.edu.likelion.OrderManagement.repository.OrderDetailRepository;
import vn.edu.likelion.OrderManagement.service.OrderDetailService;

import java.util.List;
import java.util.Optional;

/*
 * OrderManager - OrderDetailServiceImpl
 * Author: Rains
 * Date: 15/8/2024
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailEntity create(OrderDetailEntity orderDetailEntity) {
        return orderDetailRepository.save(orderDetailEntity);
    }

    @Override
    public OrderDetailEntity update(OrderDetailEntity orderDetailEntity) {
        if (orderDetailRepository.existsById(orderDetailEntity.getId())) {
            return orderDetailRepository.save(orderDetailEntity);
        } else {
            throw new RuntimeException("Order Detail not found with id: " + orderDetailEntity.getId());
        }
    }

    @Override
    public boolean delete(OrderDetailEntity orderDetailEntity) {
        if (orderDetailRepository.existsById(orderDetailEntity.getId())) {
            orderDetailRepository.deleteById(orderDetailEntity.getId());
            return true;
        } else {
            throw new RuntimeException("Order Detail not found with id: " + orderDetailEntity.getId());
        }
    }

    @Override
    public List<OrderDetailEntity> findAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetailEntity> findById(int id) {
        return orderDetailRepository.findById(id);
    }

    public List<OrderDetailEntity> getOrderDetailsByOrderId(int orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}