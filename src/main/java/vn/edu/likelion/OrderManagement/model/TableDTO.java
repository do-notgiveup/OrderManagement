package vn.edu.likelion.OrderManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {

    private int id;
    private String name;
    private boolean status;
    private double totalPrice;
    private int totalDishes;

}