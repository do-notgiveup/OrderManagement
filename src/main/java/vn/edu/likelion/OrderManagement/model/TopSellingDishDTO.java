package vn.edu.likelion.OrderManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopSellingDishDTO extends DishDTO {
    private int quantitySold; // So luong da ban cua mon an
}
