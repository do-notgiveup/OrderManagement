package vn.edu.likelion.OrderManagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;
import vn.edu.likelion.OrderManagement.repository.BookingRepository;
import vn.edu.likelion.OrderManagement.service.BookingService;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public BookingEntity create(BookingEntity booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return bookingRepository.save(bookingEntity);
    }

    @Override
    public boolean delete(BookingEntity bookingEntity) {
        if (bookingRepository.existsById(bookingEntity.getId())) {
            bookingRepository.delete(bookingEntity);
            return true;
        } else {
            throw new EntityNotFoundException("Booking not found with id: " + bookingEntity.getId());
        }
    }

    @Override
    public List<BookingEntity> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<BookingEntity> findById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public BookingDTO createBooking(BookingEntity booking) {
        BookingEntity createdBooking = bookingRepository.save(booking);
        return convertToDTO(createdBooking);
    }

    // convertToDTO for BookingDTO
    private BookingDTO convertToDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(bookingEntity.getId());
        bookingDTO.setCustomerName(bookingEntity.getName());
        bookingDTO.setCustomerAddress(bookingEntity.getAddress());
        bookingDTO.setPhoneNumber(bookingEntity.getPhoneNumber());
        bookingDTO.setBookingTime(bookingEntity.getBookingDate());
        bookingDTO.setSeat(bookingEntity.getSeat());

        return bookingDTO;
    }
}
