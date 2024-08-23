package vn.edu.likelion.OrderManagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
import java.util.stream.Collectors;

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

    @Override
    public BookingDTO updateBooking(BookingEntity bookingEntity) {
        if (bookingRepository.existsById(bookingEntity.getId())) {
            BookingEntity updateBooking = bookingRepository.save(bookingEntity);
            return convertToDTO(updateBooking);
        } else {
            throw new RuntimeException("Boking not found with id: " + bookingEntity.getId());
        }
    }

    @Override
    public BookingEntity update(BookingEntity bookingEntity) {
        return bookingRepository.save(bookingEntity);
    }

    @Override
    public boolean delete(BookingEntity bookingEntity) {
        if (bookingRepository.existsById(bookingEntity.getId())) {
            bookingEntity.setDeleted(true);
            bookingRepository.save(bookingEntity);
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
        // Convert Page to List for filter
        List<BookingDTO> bookingDTOs = bookingEntities.stream()
                .filter(bookingEntity -> !bookingEntity.isDeleted())
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(bookingDTOs, pageable, bookingEntities.getTotalElements()); //Return PageImpl Constructor
    }

    @Override
    public Optional<BookingEntity> findById(int id) {
        return bookingRepository.findById(id);
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
