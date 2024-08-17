package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceService extends BaseCRUD<InvoiceEntity> {

    List<InvoiceEntity> getInvoicesByDate(LocalDate date);

    List<InvoiceEntity> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<InvoiceEntity> getInvoicesByMonth(int year, int month);
}