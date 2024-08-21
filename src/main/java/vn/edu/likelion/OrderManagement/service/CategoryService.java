package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.CategoryEntity;
import vn.edu.likelion.OrderManagement.model.CategoryDTO;

import java.util.List;

public interface CategoryService extends BaseCRUD<CategoryEntity> {

    List<CategoryDTO> findAllCategory();

}
