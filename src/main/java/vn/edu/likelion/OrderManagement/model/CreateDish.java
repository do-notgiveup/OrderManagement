package vn.edu.likelion.OrderManagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDish {
    private String name;
    private String description;
    private String price;
    private String image;
    private boolean status;
    private int category_id;
}
