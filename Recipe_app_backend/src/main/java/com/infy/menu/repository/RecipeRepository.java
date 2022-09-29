package com.infy.menu.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.infy.menu.entity.RecipeEntity;

// Repository Class
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity,Long>{

	// Find all Veg recipe from List
	@Query("select c from RecipeEntity c where c.type=?1")
	List<RecipeEntity> findBuyVeg(String type);

	// Find all the recipe whose servering capacity is greater than or equal to given value
	@Query("select c from RecipeEntity c where c.serve>=?1")
	List<RecipeEntity> findbuyserving(int numserve);

	// @Query("SELECT * FROM RecipeEntity where serve>=?1 and ingredient Like '%?2%' ")
	@Query("select c from RecipeEntity c where c.serve>=?1 and LOCATE(?2, ingredient) > 0")
	List<RecipeEntity> findrepobyserver_ingredient(int no_serving,String specific_ingredient);


	
}

