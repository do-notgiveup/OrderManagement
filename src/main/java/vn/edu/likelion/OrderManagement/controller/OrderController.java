package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.OrderEntity;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.service.OrderService;
import vn.edu.likelion.OrderManagement.service.TableService;
import vn.edu.likelion.OrderManagement.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
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

    @GetMapping
    public ResponseEntity<List<OrderRequest>> getAllOrder() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OrderRequest>> findById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.findOrderById(id));
    }

    @GetMapping("/by-table/{id}")
    public ResponseEntity<OrderRequest> findByTable(@PathVariable int id) {
        try {
            return ResponseEntity.ok(orderService.findOrderByTable(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
