package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.TableDTO;

import java.util.List;

public interface TableService extends BaseCRUD<TableEntity> {

    List<TableDTO> findAllTables();
    List<TableEntity> sortTable();
}
