package vn.edu.likelion.OrderManagement.service;

import vn.edu.likelion.OrderManagement.entity.DishEntity;

import java.util.List;
import java.util.Optional;

public interface BaseCRUD<T> {
    T create(T t);

    T update(T t);

    boolean delete(T t);

    List<T> findAll();

    Optional<T> findById(int id);
}
