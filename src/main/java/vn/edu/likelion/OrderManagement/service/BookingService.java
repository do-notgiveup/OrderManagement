package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;

public interface BookingService extends BaseCRUD<BookingEntity> {
    BookingDTO createBooking(BookingEntity bookingEntity);
}



