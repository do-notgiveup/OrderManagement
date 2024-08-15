package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.repository.OrderRepository;
import vn.edu.likelion.OrderManagement.service.OrderService;

import java.util.Iterator;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

//    @Autowired
//    private UserRepository userRepository;

    @Override
    public OrderEntity create(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public void delete(OrderEntity orderEntity) {
        orderRepository.delete(orderEntity);
    }

    @Override
    public Iterator<OrderEntity> findAll() {
        return null;
    }

    @Override
    public Optional<OrderEntity> findById(int id) {
        return Optional.empty();
    }
}
