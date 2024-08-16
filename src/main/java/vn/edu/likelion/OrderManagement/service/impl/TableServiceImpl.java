package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.repository.TableRepository;
import vn.edu.likelion.OrderManagement.service.TableService;

import java.util.List;
import java.util.Optional;

/*
 * OrderManager - Manage Table (CRUD)
 * Author: Rains
 * Date: 15/8/2024
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public TableEntity create(TableEntity tableEntity) {
        return tableRepository.save(tableEntity);
    }

    @Override
    public TableEntity update(TableEntity tableEntity) {
        if (tableRepository.existsById(tableEntity.getId())) {
            return tableRepository.save(tableEntity);
        } else {
            throw new RuntimeException("Table not found with id: " + tableEntity.getId());
        }
    }

    @Override
    public boolean delete(TableEntity tableEntity) {
        tableRepository.findById(tableEntity.getId())
                .orElseThrow(() -> new RuntimeException("Table not found with id: " + tableEntity.getId()));
        tableRepository.delete(tableEntity);
        return true;
    }

    @Override
    public List<TableEntity> findAll() {
        return tableRepository.findAll();
    }

    @Override
    public Optional<TableEntity> findById(int id) {
        return tableRepository.findById(id);
    }
}