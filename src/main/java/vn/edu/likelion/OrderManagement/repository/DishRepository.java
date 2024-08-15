package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.DishEntity;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {

}
