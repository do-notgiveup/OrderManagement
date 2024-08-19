package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.TableDTO;
import vn.edu.likelion.OrderManagement.repository.TableRepository;
import vn.edu.likelion.OrderManagement.service.TableService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public TableEntity createTable(TableDTO tableDTO) {
        TableEntity newTableEntity =  TableEntity.builder()
                .name(tableDTO.getName())
                .status(tableDTO.isStatus())
                .build();
        return tableRepository.save(newTableEntity);
    }

    @Override
    public TableEntity create(TableEntity table) {
        return tableRepository.save(table);
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

    @Override
    public List<TableDTO> findAllTables() {
        List<TableEntity> tableEntities = tableRepository.findAll();
        return tableEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //convertToDTO
    private TableDTO convertToDTO(TableEntity tableEntity) {
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(tableEntity.getId());
        tableDTO.setName(tableEntity.getName());
        tableDTO.setStatus(tableEntity.isStatus());
        return tableDTO;
    }

    @Override
    public List<TableEntity> sortTable() {
        List<TableEntity> list = tableRepository.findAll();
        list.sort((b1, b2) -> Double.compare(b1.getId(), b2.getId()));
        return list;
    }
}