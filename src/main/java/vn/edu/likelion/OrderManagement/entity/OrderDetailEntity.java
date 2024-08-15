package vn.edu.likelion.OrderManagement.entity;

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

    @Column
    private int quantity;

    @Column
    private double pricePerItem;

    @ManyToOne
    @JoinColumn(name = "oder_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private DishEntity dish;
}
