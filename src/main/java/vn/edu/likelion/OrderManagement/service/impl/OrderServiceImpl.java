package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;

import vn.edu.likelion.OrderManagement.model.OrderDetailRequest;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.repository.*;
import vn.edu.likelion.OrderManagement.service.OrderService;

import java.util.Optional;

import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DishRepository dishRepository;

    @Override
    public OrderEntity create(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
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

    @Override
    public OrderEntity createOrder(OrderRequest order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .user(userRepository.findById(order.getUserId())
                        .orElseThrow(() -> new RuntimeException("UserId not found")))
                .status(false)
                .table(tableRepository.findById(order.getTableId())
                        .orElseThrow(() -> new RuntimeException("TableId not found")))
                .build();

        for (OrderDetailRequest orderDetailRequest : order.getOrderDetailRequests()) {
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .quantity(orderDetailRequest.getQuantity())
                    .pricePerItem(orderDetailRequest.getPricePerItem())
                    .dish(dishRepository.findById(orderDetailRequest.getDishId()).get())
                    .order(orderEntity)
                    .build();

            orderEntity.setTotalPrice(orderDetail.getPricePerItem());

            orderRepository.save(orderEntity);
            orderDetailRepository.save(orderDetail);
        }
        return orderEntity;
    }
}
