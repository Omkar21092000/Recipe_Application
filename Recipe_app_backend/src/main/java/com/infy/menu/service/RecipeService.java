package com.infy.menu.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infy.menu.entity.RecipeEntity;
import com.infy.menu.repository.RecipeRepository;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import com.infy.menu.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Service Class 
@Service("recipeServiceInterface")
public class RecipeService implements RecipeServiceInterface {

	private static Logger logger = LoggerFactory.getLogger(RecipeService.class);
    @Autowired
    RecipeRepository recipeRepository;

	// Getting new recipe in recipeList
    public String addnewfood(RecipeEntity recipe){
        recipeRepository.saveAndFlush(recipe);
        return "Recipe add in menu successfully";
    }

	// Fetching all the recipe in detials
    public List<RecipeEntity> fetchmenulist(){

		try {
			List<RecipeEntity>  recipes = recipeRepository.findAll();
			if( recipes.isEmpty()) {
				throw new Recipe_emptyException("No data in database of recipe");
			}
			return recipes;
		} catch (Exception e) {
			throw new Recipe_emptyException(e.getMessage());
		}
    }

	// Updating recipe by there ID
    public String updatemenufromlist(Long Id, RecipeEntity recipe){
		Optional<RecipeEntity> w1 = recipeRepository.findById(Id);
		if(w1.isEmpty()) {
			logger.info("In log Exception ");
			throw new NotFoundException("Recipe with given id not found.... ");
		}else {
			RecipeEntity w = w1.get();
			w.setDishname(recipe.getDishname());
	    	w.setType(recipe.getType());
	    	w.setMethodofcooking(recipe.getMethodofcooking());
	    	w.setServe(recipe.getServe());
	    	w.setIngredient(recipe.getIngredient());
	    	recipeRepository.saveAndFlush(w);
		}
		return "Data Updated Successfully";
	}

	// Deleting recipe from list by there Id
    public void removefood(Long Id) { 
		recipeRepository.deleteById(Id);
	}
    
	// Fetching all the vegetrain recipe
    public List<RecipeEntity> getallveg(String vegetarian){
    	List<RecipeEntity> flist=null;
    	flist=recipeRepository.findBuyVeg(vegetarian);
    	return flist;
    }
    
	// Fetching recipe with has number of serving > then serve with specific_ingredient
    public List<RecipeEntity> getbyingredient(Integer no_serving,String specific_ingredient){
		List<RecipeEntity> flists=null;
		try{
			flists=recipeRepository.findrepobyserver_ingredient(no_serving, specific_ingredient);
			if(flists.isEmpty()){
				throw new Recipe_emptyException("No recipe found in list");
			}
			return flists;
		}catch(Exception e){
			throw new Recipe_emptyException(e.getMessage());
		}

    }
    
	// Fetching recipe which doesn't have this ingredient and display which have methodcooking is eqaul to methodcook
    public List<RecipeEntity> getnoningredient(String ingredient,String methodcook){
    	List<RecipeEntity> flists=null;
    	int flag=0;
    	List<RecipeEntity> specificlist= new ArrayList<RecipeEntity>();
    	flists=recipeRepository.findAll();
    	for(RecipeEntity i :flists) {
    		String a=i.getIngredient();
			if(a.toLowerCase().contains(ingredient.toLowerCase())){
				flag=1;
			}else{
				flag=0;
			}
			if(flag==0 && i.getMethodofcooking().toLowerCase().equals(methodcook.toLowerCase())) {
				specificlist.add(i);
				flag=0;	
			}
    	}
    	return specificlist;	
    }

	public RecipeService(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}   

	
}
