package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.repository.InvoiceRepository;
import vn.edu.likelion.OrderManagement.service.InvoiceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceEntity> getInvoicesByDate(LocalDate date) {
        return invoiceRepository.findByInvoiceDate(date);
    }

    @Override
    public List<InvoiceEntity> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findByInvoiceDateBetween(startDate, endDate);
    }

    @Override
    public List<InvoiceEntity> getInvoicesByMonth(int year, int month) {
        return invoiceRepository.findByInvoiceMonth(year, month);
    }

    @Override
    public InvoiceEntity create(InvoiceEntity invoiceEntity) {
        return null;
    }

    @Override
    public InvoiceEntity update(InvoiceEntity invoiceEntity) {
        return null;
    }

    @Override
    public boolean delete(InvoiceEntity invoiceEntity) {
        return false;
    }

    @Override
    public List<InvoiceEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<InvoiceEntity> findById(int id) {
        return Optional.empty();
    }
}