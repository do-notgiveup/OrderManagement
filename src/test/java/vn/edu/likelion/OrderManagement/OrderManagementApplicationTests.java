package vn.edu.likelion.OrderManagement;

import org.hibernate.annotations.LazyGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import vn.edu.likelion.OrderManagement.entity.DishEntity;
import vn.edu.likelion.OrderManagement.service.DishService;

@SpringBootTest
class OrderManagementApplicationTests {

	@Autowired
	DishService dishService;

	@Test
	@Lazy
	void contextLoads() {
		System.out.println(dishService.getTopSellingDishes().stream().findFirst().get());
	}

}
