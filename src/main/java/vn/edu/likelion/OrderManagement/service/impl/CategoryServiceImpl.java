package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.UserEntity;
import vn.edu.likelion.OrderManagement.service.CategoryService;

import java.util.Iterator;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public UserEntity create(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return null;
    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public Iterator<UserEntity> findAll() {
        return null;
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        return Optional.empty();
    }
}
