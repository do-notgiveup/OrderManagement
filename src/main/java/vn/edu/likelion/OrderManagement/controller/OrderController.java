package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.service.OrderService;
import vn.edu.likelion.OrderManagement.service.TableService;
import vn.edu.likelion.OrderManagement.service.UserService;

@RestController
@CrossOrigin(origins = {"http://192.168.18.81:5173", "http://localhost:9999", "jdbc:mysql://viaduct.proxy.rlwy.net:37349/railway"}
        , allowCredentials = "true")
@RequestMapping("/api/v1/auth/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private TableService tableService;

    @PostMapping
    public OrderEntity create(@RequestBody OrderRequest order) {

        return orderService.createOrder(order);
    }
}
