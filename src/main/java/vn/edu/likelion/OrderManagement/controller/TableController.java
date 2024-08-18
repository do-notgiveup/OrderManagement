package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.TableDTO;
import vn.edu.likelion.OrderManagement.service.TableService;

import java.util.List;

/*
 * OrderManager - Table Controller (CRUD)
 * Author: Rains
 * Date: 15/8/2024
 */
@RestController
@RequestMapping("/api/v1/auth/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAllTable() {
        return ResponseEntity.ok(tableService.findAllTables());
    }

    // Get table by Id
    @GetMapping("/{id}")
    public ResponseEntity<TableEntity> getById(@PathVariable int id) {
        TableEntity tableEntity = tableService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(tableEntity);
    }

    // Create Table
    @PostMapping
    public ResponseEntity<TableEntity> createTable(@RequestBody TableEntity table) {
        TableEntity createdTable = tableService.create(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTable);
    }

    // Update Table Infor
    @PutMapping("/{id}")
    public ResponseEntity<TableEntity> updateTable(@PathVariable int id, @RequestBody TableEntity table) {
        // Check table exist
        tableService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found!"));
        table.setId(id);
        TableEntity updatedTable = tableService.update(table);
        return ResponseEntity.ok(updatedTable);
    }

    // Delete Table
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTable(@PathVariable int id) {
        TableEntity tableEntity = tableService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found!"));
        tableService.delete(tableEntity);
        //return ResponseEntity.noContent().build();
        return ResponseEntity.ok("Table deleted successfully.");
    }

    // Table sorting
    @GetMapping("/sort")
    public ResponseEntity<List<TableEntity>> sortTable() {
        return  ResponseEntity.ok((tableService.sortTable()));
    }
}