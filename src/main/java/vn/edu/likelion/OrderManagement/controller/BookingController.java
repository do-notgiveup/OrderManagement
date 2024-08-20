package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.service.BookingService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public Page<BookingDTO> getAllBookings(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "asc") String sortDirection) {
        return bookingService.findAllBookings(page, size, sortBy, sortDirection);
    }

    @PostMapping
    public BookingDTO create(@RequestBody BookingDTO booking) {
        return bookingService.createBooking(booking);
    }
}