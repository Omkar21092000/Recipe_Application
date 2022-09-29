package com.infy.menu.serviceTest;

import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import com.infy.menu.repository.RecipeRepository;
import com.infy.menu.service.RecipeService;
import com.infy.menu.entity.RecipeEntity;

// Testing on service layer
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;
    private RecipeService recipeService;

	@Autowired
	private RecipeEntity recipeEntity;

    @BeforeEach
	void setUp() {
		this.recipeService =new RecipeService(this.recipeRepository);
	}
	
	// Add____new____recipe
    @Test
	void testAddRecipe() {
		RecipeEntity r = new RecipeEntity("Dal_Makni","Veg","stove","dal,toor dala,cumin,masala",6);
		recipeService.addnewfood(r);
		verify(recipeRepository).saveAndFlush(r);
	}

	// Delete___recipe___by___id
    @Test
	void testDeleteRecipe() {
		int recipeId = 22;
		recipeService.removefood((long)recipeId);
		verify(recipeRepository).deleteById((long)recipeId);
	}
    
}
