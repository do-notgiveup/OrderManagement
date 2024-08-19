package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;

import vn.edu.likelion.OrderManagement.model.OrderDetailRequest;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.repository.*;
import vn.edu.likelion.OrderManagement.service.OrderService;

import java.time.LocalDate;
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

    @Autowired
    private InvoiceRepository invoiceRepository;

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
        //Set data order
        OrderEntity orderEntity = OrderEntity.builder()
                .user(userRepository.findById(order.getUserId())
                        .orElseThrow(() -> new RuntimeException("UserId not found")))
                .status(false)
                .table(tableRepository.findById(order.getTableId())
                        .orElseThrow(() -> new RuntimeException("TableId not found")))
                .build();

        // Set data orderDetail
        for (OrderDetailRequest orderDetailRequest : order.getOrderDetailRequests()) {
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .quantity(orderDetailRequest.getQuantity())
                    .pricePerItem(orderDetailRequest.getPricePerItem())
                    .dish(dishRepository.findById(orderDetailRequest.getDishId()).get())
                    .order(orderEntity)
                    .build();

            orderEntity.setTotalPrice(orderEntity.getTotalPrice()
                    + orderDetail.getPricePerItem() * orderDetail.getQuantity());

            orderRepository.save(orderEntity);
            orderDetailRepository.save(orderDetail);
        }

        // Set data Invoice
        InvoiceEntity invoiceEntity = InvoiceEntity.builder()
                .invoiceDate(LocalDate.now())
                .order(orderEntity)
                .totalAmount(orderEntity.getTotalPrice())
                .build();
        invoiceRepository.save(invoiceEntity);

        return orderEntity;
    }
}
