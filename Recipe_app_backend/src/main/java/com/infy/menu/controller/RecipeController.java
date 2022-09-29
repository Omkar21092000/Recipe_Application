package com.infy.menu.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.infy.menu.entity.RecipeEntity;
import com.infy.menu.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Controller Class
@RestController
@RequestMapping("/menu")
public class RecipeController {
	private static Logger logger = LoggerFactory.getLogger(RecipeService.class);
	@Autowired
	RecipeService recipeService;

	// Adding New recipe
	@PostMapping("/add")
	@CrossOrigin(origins = "http://localhost:4200")
	 public ResponseEntity<String> insertNewFood(@RequestBody RecipeEntity recipe) {
	 	String response=this.recipeService.addnewfood(recipe);
	 	return ResponseEntity.ok(response);
	 }
	
	//  Getting all recipe details
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:4200")
	 public ResponseEntity <List<RecipeEntity>>fetchAvailabledish(){
		return new ResponseEntity<>(this.recipeService.fetchmenulist(),HttpStatus.OK);
	 }
	
	//  Upadting recipe from recipe list by there Id
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateMenu(@PathVariable("id") Long id, @RequestBody RecipeEntity recipedetails) {
		try{
			ResponseEntity<String> e = new ResponseEntity<>(this.recipeService.updatemenufromlist(id,recipedetails), HttpStatus.ACCEPTED);
			return e;

		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}

	//  Deleting recipe from recipe list by there Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> removefoodfromlist(@PathVariable("id") Long id) {
		try {
			recipeService.removefood(id);
			return ResponseEntity.ok().body("Recipe deleted successfully with given id : " + id);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id +" id not found");
		}
	}
	 
	// Getting all the recipe which is vegetarian
	 @GetMapping("/{vegeterian}")
	 public ResponseEntity<List<RecipeEntity>> getAllVegDish(@PathVariable String vegeterian){
		 return new ResponseEntity<>(this.recipeService.getallveg(vegeterian),HttpStatus.OK);
	 }
	  

	//  Recipe that can serve number of person which has specific ingredient in recipe-ingredient
	 @GetMapping("/getingredient/{no_serving}/{specific_ingedient}")
	 public ResponseEntity<List<RecipeEntity>>getbyspecific(@PathVariable Integer no_serving,@PathVariable String specific_ingedient){
		try{
			return new ResponseEntity<>(this.recipeService.getbyingredient(no_serving,specific_ingedient),HttpStatus.OK);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	 }
	 
	//  Recipes without specific ingredient but that has specific cooking method
	 @GetMapping("/notiningredient/{ingredient}/{methodcook}")
	 public ResponseEntity<List<RecipeEntity>> getotherlist(@PathVariable String ingredient,@PathVariable String methodcook){
		try{
			return new ResponseEntity<>(this.recipeService.getnoningredient(ingredient,methodcook),HttpStatus.OK);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	 }
		 
}
