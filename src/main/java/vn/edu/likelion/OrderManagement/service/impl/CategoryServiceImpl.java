package vn.edu.likelion.OrderManagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.CategoryEntity;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.entity.TableEntity;
import vn.edu.likelion.OrderManagement.model.CategoryDTO;
import vn.edu.likelion.OrderManagement.model.TableDTO;
import vn.edu.likelion.OrderManagement.repository.CategoryRepository;
import vn.edu.likelion.OrderManagement.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            categoryEntity.setDeleted(true);
            categoryRepository.save(categoryEntity);
            return true;
        } else {
            throw new EntityNotFoundException("Category not found with id: " + categoryEntity.getId());
        }
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryDTO> findAllCategory() {
    List<CategoryEntity> categoryEntity = categoryRepository.findAll();
        return categoryEntity.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryEntity> findById(int id) {
        return categoryRepository.findById(id);
    }

    //convertToDTO
    private CategoryDTO convertToDTO(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setImage(categoryEntity.getImage());
        return categoryDTO;
    }
}