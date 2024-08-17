package vn.edu.likelion.OrderManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_order_detail")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDetailEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // số lượng của 1 món
    @Column
    private int quantity;

    // giá của món
    @Column
    private double pricePerItem;

    // ghi chú, vd: tí cay
    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "oder_id", nullable = false)
    @JsonManagedReference
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    @JsonManagedReference
    private DishEntity dish;
}
