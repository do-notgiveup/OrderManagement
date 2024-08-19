package vn.edu.likelion.OrderManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    // tên bàn
    @Column
    private String name;

    // trạng thái bàn đang bận hay rảnh (0: rảnh, 1: bận)
    @Column
    private boolean status;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}
