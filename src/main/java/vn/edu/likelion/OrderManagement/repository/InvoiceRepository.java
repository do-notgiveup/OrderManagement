package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {

    // Tim tat ca hoa don trong 1 ngay cu the
    @Query("SELECT i FROM InvoiceEntity i WHERE DATE(i.invoiceDate) = :date")
    List<InvoiceEntity> findByInvoiceDate(LocalDate date);

    // Tim tat ca hoa don trong 1 thoi gian cu the
    @Query("SELECT i FROM InvoiceEntity i " +
            "WHERE i.invoiceDate BETWEEN :startDate AND :endDate")
    List<InvoiceEntity> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);

    // Tim tat ca hoa don trong 1 thang cu the
    @Query("SELECT i FROM InvoiceEntity i WHERE YEAR(i.invoiceDate) = :year AND MONTH(i.invoiceDate) = :month")
    List<InvoiceEntity> findByInvoiceMonth(int year, int month);
}