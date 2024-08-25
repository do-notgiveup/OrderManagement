package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.OrderDetailEntity;
import vn.edu.likelion.OrderManagement.repository.OrderDetailRepository;
import vn.edu.likelion.OrderManagement.repository.OrderRepository;
import vn.edu.likelion.OrderManagement.service.OrderDetailService;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Delete OrderDetail
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable int id) {
        Optional<OrderDetailEntity> orderDetail = orderDetailService.findById(id);
        if (orderDetail.isPresent()) {
            orderDetailService.delete(orderDetail.get());
            return ResponseEntity.ok("Order detail deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
