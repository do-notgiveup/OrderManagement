package vn.edu.likelion.OrderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.OrderManagement.model.CategoryDTO;
import vn.edu.likelion.OrderManagement.service.CategoryService;

import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAllCategory();
    }
}
