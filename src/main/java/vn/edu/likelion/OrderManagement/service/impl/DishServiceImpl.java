package vn.edu.likelion.OrderManagement.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.model.DishDTO;
import vn.edu.likelion.OrderManagement.model.TopSellingDishDTO;
import vn.edu.likelion.OrderManagement.repository.DishRepository;
import vn.edu.likelion.OrderManagement.repository.OrderDetailRepository;
import vn.edu.likelion.OrderManagement.service.DishService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * OrderManager - DishServiceImpl
 * Author: Rains
 * Date: 15/8/2024
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public DishDTO createDish(DishEntity dishEntity) {
        DishEntity createdDish = dishRepository.save(dishEntity);
        return convertToDTO(createdDish);
    }

    @Override
    public DishEntity create(DishEntity dishEntity) {
        return dishRepository.save(dishEntity);
    }

    @Override
    public DishEntity update(DishEntity dishEntity) {
        if (dishRepository.existsById(dishEntity.getId())) {
            return dishRepository.save(dishEntity);
        } else {
            throw new RuntimeException("Dish not found with id: " + dishEntity.getId());
        }
    }

    @Override
    public boolean delete(DishEntity dishEntity) {
        if (dishRepository.existsById(dishEntity.getId())) {
            dishRepository.delete(dishEntity);
            return true;
        } else {
            throw new EntityNotFoundException("Dish not found with id: " + dishEntity.getId());
        }
    }

    @Override
    public List<DishEntity> findAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<DishEntity> findById(int id) {
        return dishRepository.findById(id);
    }

    @Override
    public Page<DishDTO> findAllDishes(int page, int size, String sortBy, String sortDirection, int category) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DishEntity> dishEntities = null;
        if (category == 0){
            dishEntities = dishRepository.findByIsDeleted(pageable, false);
        } else {
            dishEntities = dishRepository.findByIsDeletedAndCategoryId(pageable, false, category);
        }

        return dishEntities.map(this::convertToDTO);
    }

    @Override
    public DishDTO findByDishId(int id) {
        return dishRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<DishDTO> getDishesByCategory(int categoryId) {
        List<DishEntity> dishEntities = dishRepository.findByCategoryId(categoryId);
        return dishEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishDTO> searchDishes(String keyword) {
        List<DishEntity> dishEntities = dishRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
        return dishEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TopSellingDishDTO> getTopSellingDishes() {
        LocalDate now = LocalDate.now();
        LocalDate thirtyDaysAgo = now.minusDays(30);

        List<Object[]> result = orderDetailRepository.findTopSellingDishes(thirtyDaysAgo, now);

        return result.stream()
                .map(row -> {
                    DishEntity dish = (DishEntity) row[0];
                    int quantitySold = ((Long) row[1]).intValue();

                    // Tạo TopSellingDishDTO trực tiếp từ DishEntity
                    TopSellingDishDTO topSellingDishDTO = new TopSellingDishDTO();
                    topSellingDishDTO.setId(dish.getId());
                    topSellingDishDTO.setName(dish.getName());
                    topSellingDishDTO.setDescription(dish.getDescription());
                    topSellingDishDTO.setPrice(dish.getPrice());
                    topSellingDishDTO.setImage(dish.getImage());
                    topSellingDishDTO.setStatus(dish.isStatus());
                    topSellingDishDTO.setCategoryId(dish.getCategory().getId());
                    topSellingDishDTO.setQuantitySold(quantitySold);

                    return topSellingDishDTO;
                })
                .collect(Collectors.toList());
    }

    // convertToDTO
    private DishDTO convertToDTO(DishEntity dishEntity) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(dishEntity.getId());
        dishDTO.setName(dishEntity.getName());
        dishDTO.setDescription(dishEntity.getDescription());
        dishDTO.setPrice(dishEntity.getPrice());
        dishDTO.setImage(dishEntity.getImage());
        dishDTO.setStatus(dishEntity.isStatus());

        if (dishEntity.getCategory() != null) {
            dishDTO.setCategoryId(dishEntity.getCategory().getId());
        }

        return dishDTO;
    }
}