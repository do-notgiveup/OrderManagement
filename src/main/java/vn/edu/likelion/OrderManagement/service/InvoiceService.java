package vn.edu.likelion.OrderManagement.service;

import org.springframework.data.domain.Page;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.model.DishDTO;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceService extends BaseCRUD<InvoiceEntity> {

    List<InvoiceDTO> getInvoicesByDate(LocalDate date);

    List<InvoiceDTO> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<InvoiceDTO> getInvoicesByMonth(int year, int month);

    Page<InvoiceDTO> findAllInvoices(int page, int size, String sortBy, String sortDirection);
}