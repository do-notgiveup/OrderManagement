package vn.edu.likelion.OrderManagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, updatable = false)
    @CreatedDate
    private LocalDate createTime = LocalDate.now();

    @Column(nullable = true, insertable = false)
    @LastModifiedDate
    private LocalDate updateTime = LocalDate.now();

    @Column(nullable = true)
    private boolean isDeleted = false;
}
