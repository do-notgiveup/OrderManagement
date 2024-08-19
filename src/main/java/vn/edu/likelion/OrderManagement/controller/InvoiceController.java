package vn.edu.likelion.OrderManagement.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.model.DishDTO;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;
import vn.edu.likelion.OrderManagement.service.InvoiceService;
import vn.edu.likelion.OrderManagement.service.impl.ReportService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ReportService reportService;

    // Tim tat ca hoa don trong ngay
    @GetMapping("/bydate")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByDate(@RequestParam LocalDate date) {
        List<InvoiceEntity> invoices = invoiceService.getInvoicesByDate(date);
        return ResponseEntity.ok(invoices.stream()
                .map(this::convertToInvoiceDTO)
                .collect(Collectors.toList()));
    }

    // tat ca hoa don trong 1 khoan thoi gian
    @GetMapping("/by-date-range")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
//        LocalDateTime startOfDay = startDate.atStartOfDay();
//        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
        List<InvoiceEntity> invoices = invoiceService.getInvoicesByDateRange(startDate, endDate);
        return ResponseEntity.ok(invoices.stream()
                .map(this::convertToInvoiceDTO)
                .collect(Collectors.toList()));
    }

    // tat ca hoa don trong thang
    @GetMapping("/by-month")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByMonth(
            @RequestParam int year,
            @RequestParam int month
    ) {
        List<InvoiceEntity> invoices = invoiceService.getInvoicesByMonth(year, month);
        return ResponseEntity.ok(invoices.stream()
                .map(this::convertToInvoiceDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/export/by-date/excel")
    public ResponseEntity<InputStreamResource> exportInvoicesByDateToExcel(@RequestParam LocalDate date) {
        ByteArrayInputStream in = null;
        in = reportService.exportInvoicesByDate(date);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=invoices_" + date + ".xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/export/by-date-range/excel")
    public ResponseEntity<InputStreamResource> exportInvoicesByDateRangeToExcel(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) throws IOException {
//        LocalDateTime startOfDay = startDate.atStartOfDay();
//        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
        ByteArrayInputStream in = reportService.exportInvoicesByDateRange(startDate, endDate);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=invoices_" + startDate + "_to_" + endDate + ".xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    @GetMapping("/export/by-month/excel")
    public ResponseEntity<InputStreamResource> exportInvoicesByMonthToExcel(
            @RequestParam int year,
            @RequestParam int month
    ) {
        ByteArrayInputStream in = reportService.exportInvoicesByMonth(year, month);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=invoices_" + year + "_" + month + ".xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }

    // convertToDTO for InvoiceDTO
    private InvoiceDTO convertToInvoiceDTO(InvoiceEntity invoiceEntity) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoiceEntity.getId());
        invoiceDTO.setOrderId(invoiceEntity.getOrder().getId());
        invoiceDTO.setInvoiceDate(invoiceEntity.getInvoiceDate());
        invoiceDTO.setTotalAmount(invoiceEntity.getTotalAmount());

        return invoiceDTO;
    }
}