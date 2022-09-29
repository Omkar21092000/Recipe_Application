package com.infy.menu.respositoryTest;

import com.infy.menu.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import com.infy.menu.entity.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Test for Repository layer
@SpringBootTest
public class RecipeTestRepository { 

	@Autowired
	private RecipeRepository recipeRepository;

	// Get All recipe whoes type is Vegetarian
	@Test
	void getallVeg(){
		List<RecipeEntity> allvegitems=recipeRepository.findBuyVeg("Veg");
		assertFalse(allvegitems.isEmpty());
	}

	// Get the recipe which have specific ingredient and num of serve
	@Test
	void getbyserveandingrediet(){
		List<RecipeEntity> serve_ingredientList=recipeRepository.findrepobyserver_ingredient(3, "haldi");
		assertFalse(serve_ingredientList.isEmpty());
	}
	   
}
