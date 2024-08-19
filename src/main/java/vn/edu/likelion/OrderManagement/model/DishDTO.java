package vn.edu.likelion.OrderManagement.model;

import lombok.Data;

@Data
public class DishDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private boolean status;
    private int categoryId;
}