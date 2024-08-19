package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.model.DishDTO;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;
import vn.edu.likelion.OrderManagement.repository.InvoiceRepository;
import vn.edu.likelion.OrderManagement.service.InvoiceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDTO> getInvoicesByDate(LocalDate date) {
        return invoiceRepository.findByInvoiceDate(date).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return invoiceRepository.findByInvoiceDateBetween(startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getInvoicesByMonth(int year, int month) {
        return invoiceRepository.findByInvoiceMonth(year, month).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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

    @Override
    public Page<InvoiceDTO> findAllInvoices(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<InvoiceEntity> dishEntities = invoiceRepository.findAll(pageable);
        return dishEntities.map(this::convertToDTO);
    }

    // convertToDTO for InvoiceDTO
    private InvoiceDTO convertToDTO(InvoiceEntity invoiceEntity) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoiceEntity.getId());
        invoiceDTO.setOrderId(invoiceEntity.getOrder().getId());
        invoiceDTO.setInvoiceDate(invoiceEntity.getInvoiceDate());
        invoiceDTO.setTotalAmount(invoiceEntity.getTotalAmount());

        return invoiceDTO;
    }
}