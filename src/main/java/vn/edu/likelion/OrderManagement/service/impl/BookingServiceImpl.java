package vn.edu.likelion.OrderManagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.model.BookingDTO;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;
import vn.edu.likelion.OrderManagement.repository.BookingRepository;
import vn.edu.likelion.OrderManagement.repository.UserRepository;
import vn.edu.likelion.OrderManagement.service.BookingService;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;

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
    public Page<BookingDTO> findAllBookings(int page, int size, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BookingEntity> bookingEntities = bookingRepository.findAll(pageable);
        return bookingEntities.map(this::convertToDTO);
    }

    @Override
    public Optional<BookingEntity> findById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public BookingDTO createBooking(BookingDTO booking) {
        BookingEntity createdBooking = bookingRepository.save(BookingEntity.builder()
                .bookingDate(booking.getBookingTime())
                .address(booking.getCustomerAddress())
                .seat(booking.getSeat())
                .name(booking.getCustomerName())
                .phoneNumber(booking.getPhoneNumber())
                .user(userRepository.findById(booking.getUserId()).get())
                .build());
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
        bookingDTO.setStatus(bookingEntity.getStatus());
        bookingDTO.setUserId(bookingEntity.getUser().getId());
        return bookingDTO;
    }
}
