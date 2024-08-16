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
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column
    private double price;

    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    @JsonManagedReference
    private TableEntity table;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<OrderDetailEntity> orderDetails;
}
