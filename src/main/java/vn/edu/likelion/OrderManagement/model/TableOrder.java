package vn.edu.likelion.OrderManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TableOrder {
    private int tableId;
    private int orderId;
    private int dishId;
    private String dishName;
    private double dishPrice;
}
