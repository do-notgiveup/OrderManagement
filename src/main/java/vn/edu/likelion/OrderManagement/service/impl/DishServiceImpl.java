package vn.edu.likelion.OrderManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.repository.DishRepository;
import vn.edu.likelion.OrderManagement.repository.OrderDetailRepository;
import vn.edu.likelion.OrderManagement.service.DishService;

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
            return false;
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

    public List<DishEntity> getDishesByCategory(int categoryId) {
        return dishRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<DishEntity> searchDishes(String keyword) {
        return dishRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public List<DishEntity> getTopSellingDishes() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyDaysAgo = now.minusDays(30);

        // Query total sold 30 days ago
        List<Object[]> result = orderDetailRepository.findTopSellingDishes(thirtyDaysAgo, now);

        // Convert to List<DishEntity>
        List<DishEntity> topSellingDishes = result.stream()
                .map(row -> {
                    DishEntity dish = (DishEntity) row[0];
                    int quantitySold = (int) row[1];
                    return dish;
                })
                .collect(Collectors.toList());
        return topSellingDishes;

    }
}