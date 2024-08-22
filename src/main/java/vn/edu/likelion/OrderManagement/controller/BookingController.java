package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;
import vn.edu.likelion.OrderManagement.model.OrderRequest;
import vn.edu.likelion.OrderManagement.service.BookingService;

import java.util.List;
import java.util.Optional;

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

    // Create
    @PostMapping
    public BookingDTO create(@RequestBody BookingDTO booking) {
        return bookingService.createBooking(booking);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id) {
        Optional<BookingEntity> dishOptional = bookingService.findById(id);
        if (dishOptional.isPresent()) {
            bookingService.delete(dishOptional.get());
            return ResponseEntity.ok("Booking deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable int id, @RequestBody BookingEntity booking) {
        BookingEntity existingBooking = bookingService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found!"));

        if (booking.getName() != null) {
            existingBooking.setName(booking.getName());
        }
        if (booking.getAddress() != null) {
            existingBooking.setAddress(booking.getAddress());
        }
        if (booking.getBookingDate() != null) {
            existingBooking.setBookingDate(booking.getBookingDate());
        }
        if (booking.getPhoneNumber() != null) {
            existingBooking.setPhoneNumber(booking.getPhoneNumber());
        }
        if (booking.getSeat() != 0) {
            existingBooking.setSeat(booking.getSeat());
        }
        if (booking.getStatus() != existingBooking.getStatus()) {
            existingBooking.setStatus(booking.getStatus());
        }
        BookingDTO updatedBooking = bookingService.updateBooking(existingBooking);
        return ResponseEntity.ok(updatedBooking);
    }
}