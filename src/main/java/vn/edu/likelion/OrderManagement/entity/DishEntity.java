package vn.edu.likelion.OrderManagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_dishes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // tên món ăn
    @Column
    private String name;

    // mô tả của món ăn
    @Column
    private String description;

    // giá của món
    @Column
    private double price;

    // hình ảnh của món ăn
    @Column
    private String image;

    // trạng thái món (0: hết, 1: còn) hoặc (0: tạm nghỉ bán, 1: đang bán)
    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private CategoryEntity category;
}
