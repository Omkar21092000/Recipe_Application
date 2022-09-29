package com.infy.menu.service;
import com.infy.menu.entity.RecipeEntity;
import java.util.List;

// Service Interface class
public interface RecipeServiceInterface{

    public String addnewfood(RecipeEntity recipe);
    public List<RecipeEntity> fetchmenulist();
    public String updatemenufromlist(Long Id, RecipeEntity recipe);
    public void removefood(Long Id);
    public List<RecipeEntity> getallveg(String vegetarian);
    public List<RecipeEntity> getbyingredient(Integer no_serving,String specific_ingredient);
    public List<RecipeEntity> getnoningredient(String ingredient,String methodcook);

}
