package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.OrderManagement.model.InvoiceDTO;
import vn.edu.likelion.OrderManagement.service.InvoiceService;
import vn.edu.likelion.OrderManagement.service.impl.ReportService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByDate(date);
        return ResponseEntity.ok(invoices);
    }

    // tat ca hoa don trong 1 khoan thoi gian
    @GetMapping("/by-date-range")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {

//        LocalDateTime startOfDay = startDate.atStartOfDay();
//        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByDateRange(startDate, endDate);
        return ResponseEntity.ok(invoices);
    }

    // tat ca hoa don trong thang
    @GetMapping("/by-month")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByMonth(
            @RequestParam int year,
            @RequestParam int month
    ) {
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByMonth(year, month);
        return ResponseEntity.ok(invoices);
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

    @GetMapping
    public ResponseEntity<Page<InvoiceDTO>> getAllDishes(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size,
                                                         @RequestParam(defaultValue = "id") String sortBy,
                                                         @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<InvoiceDTO> dishes = invoiceService.findAllInvoices(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(dishes);
    }
}