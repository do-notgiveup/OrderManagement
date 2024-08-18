package vn.edu.likelion.OrderManagement.service.impl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.InvoiceEntity;
import vn.edu.likelion.OrderManagement.service.InvoiceService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private InvoiceService invoiceService;

    public ByteArrayInputStream exportInvoicesByDate(LocalDate date) {
        List<InvoiceEntity> invoices = invoiceService.getInvoicesByDate(date);
        return exportInvoicesToExcel(invoices, "Invoices for " + date);
    }

    public ByteArrayInputStream exportInvoicesByMonth(int year, int month) {
        List<InvoiceEntity> invoices = invoiceService.getInvoicesByMonth(year, month);
        return exportInvoicesToExcel(invoices, "Invoices for " + month + "_" + year);
    }

    public ByteArrayInputStream exportInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<InvoiceEntity> invoices = invoiceService.getInvoicesByDateRange(startDate, endDate);
        return exportInvoicesToExcel(invoices, "Invoices from " + startDate + " to " + endDate);
    }

    // Phương thức private chung để xuất file Excel
    private ByteArrayInputStream exportInvoicesToExcel(List<InvoiceEntity> invoices, String sheetName) {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(sheetName);

            // Create Header
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.getCell(0).setCellStyle(headerStyle);
            headerRow.createCell(1).setCellValue("Invoice Date");
            headerRow.getCell(1).setCellStyle(headerStyle);
            headerRow.createCell(2).setCellValue("Total Price");
            headerRow.getCell(2).setCellStyle(headerStyle);

            // Get data
            int rowNum = 1;
            double totalAmount = 0.0;
            for (InvoiceEntity invoice : invoices) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(invoice.getId());
                row.createCell(1).setCellValue(invoice.getInvoiceDate().toString());

                row.createCell(2).setCellValue(invoice.getTotalAmount());
                totalAmount += invoice.getTotalAmount();
            }

            // All total price row
            Row totalRow = sheet.createRow(rowNum);
            totalRow.createCell(0).setCellValue("Total");
            totalRow.createCell(1).setCellValue(invoices.size());
            totalRow.createCell(2).setCellValue(totalAmount);

            // Auto Column size
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error exporting invoices.", e);
        }
    }
}