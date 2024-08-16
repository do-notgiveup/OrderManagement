package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.TableEntity;

import java.util.List;

public interface TableService extends BaseCRUD<TableEntity> {
    public List<TableEntity> sortTable();
}
