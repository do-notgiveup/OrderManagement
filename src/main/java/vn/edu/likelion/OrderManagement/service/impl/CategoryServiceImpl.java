package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.CategoryEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.repository.CategoryRepository;
import vn.edu.likelion.OrderManagement.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity update(CategoryEntity categoryEntity) {

        if (categoryRepository.existsById(categoryEntity.getId())) {
            return categoryRepository.save(categoryEntity);
        } else {
            throw new RuntimeException("Category not found with id: " + categoryEntity.getId());
        }
    }

    @Override
    public boolean delete(CategoryEntity categoryEntity) {
        if (categoryRepository.existsById(categoryEntity.getId())) {
            categoryRepository.delete(categoryEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryEntity> findById(int id) {
        return categoryRepository.findById(id);
    }
}