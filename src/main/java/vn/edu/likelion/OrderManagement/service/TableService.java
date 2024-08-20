package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.TableDTO;

import java.util.List;
import java.util.Optional;

public interface TableService extends BaseCRUD<TableEntity> {

    TableEntity createTable(TableDTO tableDTO);
    List<TableDTO> findAllTables();
    Optional<TableDTO> findTableById(int id);
    List<TableDTO> sortTable();
}
