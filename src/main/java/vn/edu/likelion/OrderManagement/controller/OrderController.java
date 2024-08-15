package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.service.OrderService;
import vn.edu.likelion.OrderManagement.service.UserService;

@RestController
@RequestMapping("/auth/api/v1")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping
    public OrderEntity create(@RequestBody OrderRequest order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .user(userService.findById(order.getUserId())
                        .orElseThrow(() -> new RuntimeException("UserId not found")))
                .status(false)

                .build();

        return orderService.create(orderEntity);
    }
}
