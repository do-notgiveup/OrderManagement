package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.likelion.OrderManagement.entity.BookingEntity;
import vn.edu.likelion.OrderManagement.entity.CategoryEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
}
