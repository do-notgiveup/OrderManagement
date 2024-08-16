package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.DishEntity;

import java.util.List;

public interface DishService extends BaseCRUD<DishEntity> {
    List<DishEntity> getDishesByCategory(int categoryId);
}
