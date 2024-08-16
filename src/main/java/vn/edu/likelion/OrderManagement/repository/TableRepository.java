package vn.edu.likelion.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.likelion.OrderManagement.entity.TableEntity;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Integer> {

    TableEntity findByNameIgnoreCase(String name);

    List<TableEntity> findByStatus(boolean status);



}

