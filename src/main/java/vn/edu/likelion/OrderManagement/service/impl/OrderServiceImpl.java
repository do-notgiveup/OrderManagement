package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;

import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;
import vn.edu.likelion.OrderManagement.model.OrderDetailRequest;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.repository.*;
import vn.edu.likelion.OrderManagement.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
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
            orderEntity.setDeleted(true);
            orderRepository.delete(orderEntity);
            return true;
        } else {
            throw new RuntimeException("Order Detail not found with id: " + orderEntity.getId());
        }
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderRequest> getAllOrder() {
        List<OrderEntity> orderEntity = orderRepository.findAll();
        return orderEntity.stream().map(this::convertToDTO).toList();
    }

    @Override
    public Optional<OrderEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderRequest> findOrderById(int id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        return orderEntity.map(this::convertToDTO);
    }

    @Override
    public OrderRequest createOrder(OrderRequest order) {
        // Update status table
        TableEntity tableEntity = tableRepository.findById(order.getTableId())
                .orElseThrow(() -> new RuntimeException("TableId not found"));
        tableEntity.setStatus(true);
        tableRepository.save(tableEntity);

        //Set data order
        OrderEntity orderEntity = OrderEntity.builder()
                .user(userRepository.findById(order.getUserId())
                        .orElseThrow(() -> new RuntimeException("UserId not found")))
                .status(false)
                .table(tableRepository.findById(order.getTableId())
                        .orElseThrow(() -> new RuntimeException("TableId not found")))
                .build();

        if (order.getOrderId() != 0) {
            orderEntity.setId(order.getOrderId());
        }
        orderRepository.save(orderEntity);
        // Set data orderDetail
        double total = 0;
        for (OrderDetailRequest orderDetailRequest : order.getOrderDetailRequests()) {
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .quantity(orderDetailRequest.getQuantity())
                    .pricePerItem(orderDetailRequest.getPricePerItem())
                    .dish(dishRepository.findById(orderDetailRequest.getDishId()).get())
                    .note(orderDetailRequest.getNote())
                    .order(orderEntity)
                    .build();

            // hvinhtuong
//            if (orderDetailRequest.getDishId() != 0) {
//                orderDetail.setId(orderDetailRequest.getDishId());
//                OrderDetailEntity existingOrderDetail = orderDetailRepository.findById(orderDetailRequest.getDishId())
//                        .orElseThrow(() -> new RuntimeException("OrderDetail not found with id: " + orderDetailRequest.getDishId()));
//
//                // Re-Calculate price
//                double priceChange = (orderDetail.getPricePerItem() * orderDetail.getQuantity())
//                        - (existingOrderDetail.getPricePerItem() * existingOrderDetail.getQuantity());
//                orderEntity.setTotalPrice(orderEntity.getTotalPrice() + priceChange);
//            } else {
//                orderEntity.setTotalPrice(orderEntity.getTotalPrice()
//                        + orderDetail.getPricePerItem() * orderDetail.getQuantity());
//            }

            // pdnghia
            if (orderDetailRequest.getId() != 0){
                orderDetail.setId(orderDetailRequest.getId());
            }

            //Re-Calculate price
            total += orderDetailRequest.getQuantity() * orderDetailRequest.getPricePerItem();

            orderDetailRepository.save(orderDetail);
        }

        orderEntity.setTotalPrice(total);
        orderRepository.save(orderEntity);

        return convertToDTO(orderEntity);
    }

    @Override
    public String payOrder(OrderRequest order) {
        // Update status table
        TableEntity tableEntity = tableRepository.findById(order.getTableId())
                .orElseThrow(() -> new RuntimeException("TableId not found"));
        tableEntity.setStatus(false);
        tableRepository.save(tableEntity);

        // Update status order
        OrderEntity orderEntity = orderRepository.findById(order.getOrderId())
                .orElseThrow(() -> new RuntimeException("OrderId not found"));
        orderEntity.setStatus(true);
        orderRepository.save(orderEntity);

        // Create Invoice
        InvoiceEntity invoiceEntity = InvoiceEntity.builder()
                .invoiceDate(LocalDate.now())
                .order(orderEntity)
                .totalAmount(orderEntity.getTotalPrice())
                .build();
        invoiceRepository.save(invoiceEntity);

        return "pay success";
    }

    // convertToDTO for OrderRequest
    private OrderRequest convertToDTO(OrderEntity orderEntity) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderEntity.getId());
        orderRequest.setTableId(orderEntity.getTable().getId());
        orderRequest.setTotalPrice(orderEntity.getTotalPrice());
        orderRequest.setUserId(orderEntity.getUser().getId());

        List<OrderDetailEntity> orderDetailEntities = orderDetailRepository.findByOrderId(orderEntity.getId());
        List<OrderDetailRequest> orderDetailRequests = new ArrayList<>();

        for (OrderDetailEntity orderDetailEntity : orderDetailEntities) {
            OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
            orderDetailRequest.setId(orderDetailEntity.getId());
            orderDetailRequest.setDishId(orderDetailEntity.getDish().getId());
            orderDetailRequest.setDishName(orderDetailEntity.getDish().getName());
            orderDetailRequest.setImage(orderDetailEntity.getDish().getImage());
            orderDetailRequest.setQuantity(orderDetailEntity.getQuantity());
            orderDetailRequest.setPricePerItem(orderDetailEntity.getPricePerItem());
            orderDetailRequest.setNote(orderDetailEntity.getNote());
            orderDetailRequests.add(orderDetailRequest);
        }
        orderRequest.setOrderDetailRequests(orderDetailRequests);
        return orderRequest;
    }

    public OrderRequest findOrderByTable(int tableId) {
        Optional<TableEntity> table = tableRepository.findById(tableId);

        if (table.isPresent() && table.get().isStatus()) {
            OrderEntity orderEntity = orderRepository.findByTableIdOrderByCreateTimeDesc(table.get().getId()).get(0);
            return convertToDTO(orderEntity);
        } else return null;
    }
}
