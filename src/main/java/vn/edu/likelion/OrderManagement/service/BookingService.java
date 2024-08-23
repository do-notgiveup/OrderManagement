package vn.edu.likelion.OrderManagement.service;

import org.springframework.data.domain.Page;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;

public interface BookingService extends BaseCRUD<BookingEntity> {
    BookingDTO createBooking(BookingDTO booking);
    BookingDTO updateBooking(BookingEntity bookingEntity);

    Page<BookingDTO> findAllBookings(int page, int size, String sortBy, String sortDirection);
}



