package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.service.BookingService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public BookingDTO create(@RequestBody BookingEntity booking) {
        return bookingService.createBooking(booking);
    }
}