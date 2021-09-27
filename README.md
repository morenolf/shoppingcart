# Shopping Cart

Shopping Cart application simulator as Rest API service.

Description
This Shopping cart is meant to simulate a service that can persist shopping car status for each user. Add products, delete them and calculate the total based on user, promotional date and quantity of products.
Rest API will allow to create and delete shopping carts, add or remove products to the shopping cart, calculate the shopping cart total base on strategies and finally get N more expensive products purchased by an User.
Additionaly, for testing porpuses, there is a angular project as front end. This front-end simluates login by selecting users from database with different caracteristics. Providing of a visual interface to select user, add and remove items from shopping cart and check total calculation of the shopping cart.

Out of the Scope
This project doesn't have the capability to create, delete or modify users or products. This information is already present on the database and it will be use to add or remove the products from the shopping cart.

Web API:
GET /shoppingcart/setPromotionalDates/ Set promotional date based on boolean parameter. This value will be use to calculate the total of the shopping cart.
GET /shoppingcart/listAllProducts Retrieves all the products from database.
GET /shoppingcart/validateUser Validates if a user exist using an user id as arguments. Returns a User entity. If the user doesn't have an active shopping cart it will generate one with pending status.
GET /shoppingcart/getUsers/ Retrieves all the user from database.
GET /shoppingcart/newShoppingCart/ Generates an empty shopping cart using shopping cart id as argument.
GET /shoppingcart/removeShoppingCart/ Deletes a shopping cart from database.
GET /shoppingcart/addProductToShoppingCart/ Adds a product to a shopping cart based on Key: ShoppingCartId and ProductId. Returns ShoppingCart entity with new configuration. Returns ShoppingCartKey
GET /shoppingcart/removeProductFromShoppingCart/ Removes only one product from shopping cart. If the shopping cart has more than 1 quantity for that product, it will reduce the quantity by 1. Returns ShoppingCartKey.
GET /shoppingcart/getMoreExpensiveProductsByUser/ Returns a list of more expensive products that the user has bought.

Pending task
1. Test more expensive products for a user. 
2. Generate unit test for the Rest API project.
3. Create a guide for installation.
4. Create front-end option to retrieve more expensive products for the user.
5. UML diagram to understand relationship between entities and model.
6. Define other controllers to avoid having responsabilities on ShoppingCartController that should be on other controllers/services.
7. Add Paging for List of products and users.
8. Add headers as documentation.
9. Add exception handling for service and repository layers.
10. Upload last version to Google Cloud Engine in order to test application.
11. Add details regarding scope and description of the project. Entities will be require to understand globally and the jumping into UML.
