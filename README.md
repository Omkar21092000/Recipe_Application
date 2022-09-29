# Recipe Application
Simple web page application which contain list of recipe with there detail and having some CURD base api.For the database mysql is used to perform curd base application. All the necessary dependency has been add.

# Version
Java Version 17
Mysql
Angular CLI: 13.1.3

# Database
  Name:foodlist

# API
Post request
To add data in database:http://localhost:8080/menu/add
![AddInMenu_backend](https://user-images.githubusercontent.com/91202848/191707749-eb2e22f2-d8e8-4413-babe-9fb40a66bfa2.jpg)
![adddfood_frontend](https://user-images.githubusercontent.com/91202848/191708829-60429926-2a66-4a31-8e3d-168ebc4e0fbf.jpg)
![addedfood_sucessfully](https://user-images.githubusercontent.com/91202848/191708849-79b4b8b6-3e3f-4dd5-8173-02facc2f8665.jpg)


GET request
To fetch all data:http://localhost:8080/menu/all
![homepages_fortend](https://user-images.githubusercontent.com/91202848/191708974-ac60c11f-3b36-4d3e-b8f6-48e31b523ca0.jpg)


Put request
To update data by id:http://localhost:8080/menu/update/{id}
![UpdateDish_backend](https://user-images.githubusercontent.com/91202848/191709067-3b9c6156-e2ce-4fe1-b3d0-024f883fc731.jpg)


Delete request
To delete data by id:http://localhost:8080/menu/delete/{id}
![DeleteFoodItem_backend](https://user-images.githubusercontent.com/91202848/191709115-7ef3da1f-f2bd-44e5-9cd8-bcdf321fa67a.jpg)


@Get request
Fetch Data All vegeterian
http://localhost:8080/menu/vegeterian
![GetAllVeg_backend](https://user-images.githubusercontent.com/91202848/191709185-7e1686b4-f209-4634-94f8-2451d41cedde.jpg)


@Get request
Recipes that can serve 4 persons and have "potato" as ingredient
http://localhost:8080/menu/getingredient/{numserve}/{specingedient}
For exmaple:
http://localhost:8080/menu/getingredient/4/tomato
![Get_item_by_ingredientand_numserve_backend](https://user-images.githubusercontent.com/91202848/191709277-2216c674-657c-4a00-8551-86d449650924.jpg)

@Get request
Recipes without "salmon" as an ingredient that has "oven" in instruction
http://localhost:8080/menu/notiningredient/{ingre}/{methcook}
For example:http://localhost:8080/menu/notiningredient/salmon/oven
![Ingredient_notin_dish_backend](https://user-images.githubusercontent.com/91202848/191709341-d1c047d4-fb1e-42be-812e-d5efbb614723.jpg)


Home Page for Angular :
http://localhost:4200/
To Add new dish in menu:
http://localhost:4200/adddish

Serach By value:
![Search_by_value_frontent](https://user-images.githubusercontent.com/91202848/191709541-b8ed712f-e1ff-4a8c-853d-e9bf0b21fa47.jpg)
![Search_by_value1_frontent](https://user-images.githubusercontent.com/91202848/191709559-70336733-85b2-4f0f-9d12-ad6cd1904e31.jpg)
![searchbyvalu1_frrontent](https://user-images.githubusercontent.com/91202848/191709578-b3e43a75-3ae7-4692-afa5-7aa893ad34c6.jpg)
![Searchvalue_frontent](https://user-images.githubusercontent.com/91202848/191709607-f74effa5-96e2-4bc9-9cf4-3c6603ac1ce2.jpg)






