package com.infy.menu.controllerTest;

import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.menu.controller.RecipeController;
import com.infy.menu.entity.RecipeEntity;
import com.infy.menu.service.RecipeService;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RecipeService recipeService;

    List<RecipeEntity> recipelists=new ArrayList<>();
        

    // Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

    // Before each method
    @BeforeEach
	void setUp() {
		RecipeEntity re1=new RecipeEntity();
        re1.setId(23);
        re1.setDishname("Mutton Curry");
        re1.setType( "NonVegeterian");
        re1.setMethodofcooking("Fried");
        re1.setIngredient("Mutton,Gee,Salmon,Oninon,Haldi,Cloves,Green Chiils");
        re1.setServe(3);

        RecipeEntity re2=new RecipeEntity();
        re2.setId(23);
        re2.setDishname( "ButterNaan");
        re2.setType( "Veg");
        re2.setMethodofcooking("Oven");
        re2.setIngredient("Bread,Oil,Butter,Water,Salt");
        re2.setServe(5);


       this.recipelists.add(re1);
       this.recipelists.add(re2);
	}



    // createnewrecipe by adding url /menu/add
    @Test
    public void addnewrecipefood() throws Exception{
        RecipeEntity mockTicket = new RecipeEntity();
		String inputInJson = this.mapToJson(mockTicket);
		String URI = "/menu/add";
		Mockito.when(recipeService.addnewfood(Mockito.any(RecipeEntity.class))).thenReturn(inputInJson);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
    }


    // fetch all the recipe
    @Test
    void getallrecipefromlist() throws Exception{
        Mockito.when(recipeService.fetchmenulist()).thenReturn(this.recipelists);
		
		String URI = "/menu/all";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(this.recipelists);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

    }

	// Getting all the vegetarian recipe dish
    @Test
    void getallVegtarianrecipe() throws Exception{
        Mockito.when(recipeService.getallveg(Mockito.anyString())).thenReturn(this.recipelists);
		String URI = "/menu/type?type=Veg";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(this.recipelists);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
    }

	// Getting recipe which have specific ingredient in ingredient list.
    @Test
    void getbuynumservingandingredient()throws Exception{
		Mockito.when(recipeService.getbyingredient( Mockito.anyInt(), Mockito.anyString())).thenReturn(this.recipelists);
		String URI = "/menu/getingredient/3/haldi";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(this.recipelists);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

    }

	// Getting recipe with doesn't contain ingredient in ingredient.
    @Test
    void donotgetbyingredient()throws Exception{
		Mockito.when(recipeService.getnoningredient(Mockito.anyString(), Mockito.anyString())).thenReturn(this.recipelists);
		String URI = "/menu/notiningredient/Salmon/Oven";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(this.recipelists);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);

    }









   
    
}
