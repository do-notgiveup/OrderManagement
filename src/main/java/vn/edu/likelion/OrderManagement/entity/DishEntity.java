package vn.edu.likelion.OrderManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "tbl_dishes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private double price;

    @Column
    private String image;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private CategoryEntity category;
}
