# McMenu

This application was generated using JHipster 7.9.3.

## Link project

[Link](https://mc-menu.herokuapp.com)

## How to start project

```shell
npm install # install packages
npm run java:docker:arm64 # make sure using java 11
docker-compose -f src/main/docker/app.yml up -d # start docker
```

If you want init data, please use this data [Database script](./docs/backup_mcmenu.sql).

## Database schema

![Database schema](./docs/database_schema.png)

## Sequence Diagram

- Login

```mermaid
sequenceDiagram
    User ->> Keycloak: Authentication Request
    Keycloak -->> User: Login Page
    User ->> User: Enter Login Details
    User ->> Keycloak: Submit Login
    Keycloak ->> Keycloak: Validate
    Keycloak --> User: Redirect to Application
```

- Register

```mermaid
sequenceDiagram
    User ->> Keycloak: Registration Request
    Keycloak -->> User: Register Page
    User ->> User: Enter Register Details
    User ->> Keycloak: Submit Register
    Keycloak ->> Keycloak: Validate
    Keycloak --> User: Redirect to Application
```

- Logout

```mermaid
sequenceDiagram
    User ->> Keycloak: Logout Request
    Keycloak -->> User: Clear user session
    Keycloak --> User: Redirect to Login page
```

- Create menu (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: createCategory with bearer token
  CategoryResource -->> User: CREATED Category data with url details
  User ->> IngredientsResource: createIngredients with bearer token
  IngredientsResource -->> User: CREATED Ingredients data with url details
  User ->> NutritionSummaryResource: createNutritionSummary with bearer token
  NutritionSummaryResource -->> User: CREATED NutritionSummary data with url details
  User ->> NutritionSummaryResource: createNutritionSummary with bearer token
  NutritionSummaryResource -->> User: CREATED NutritionSummary data with url details
  User ->> ProductResource: createProduct with bearer token
  ProductResource -->> User: CREATED Product data with url details
```

- Load full menu

```mermaid
sequenceDiagram
  User ->> Public_Category_Resource: getAllCategories
  Public_Category_Resource -->> User: List categories
  User ->> Public_Category_Resource: getCategoryFullMenu (for each category)
  Public_Category_Resource -->> User: List products and meals of each category
```

- Product details

```mermaid
sequenceDiagram
  User ->> Public_Product_Resource: getProductIngredients
  Public_Product_Resource ->> Ingredients_Service: findAllByProductId
  Ingredients_Service -->> Public_Product_Resource: List ingredients of product
  Public_Product_Resource ->> Nutrition_Summary_Service: findAllByProductId
  Nutrition_Summary_Service -->> Public_Product_Resource: List nutrition summary of product
  Public_Product_Resource ->> Product_Service: getRelatedProduct
  Product_Service -->> Public_Product_Resource: List related product (other size or amount of pieces)
  Public_Product_Resource -->> User: Product details data
```

- Create category (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: createCategory with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource ->> CategoryResource: Validate request
  CategoryResource -->> User: CREATED Category data with url category details
```

- Update category (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: updateCategory with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource ->> CategoryResource: Validate request
  CategoryResource -->> User: UPDATED Category data
```

- Update partial category (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: partialUpdateCategory with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource ->> CategoryResource: Validate request
  CategoryResource -->> User: UPDATED Category data
```

- List category (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: getAllCategories with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource -->> User: List categories
```

- Category detail (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: getCategory with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource -->> User: Category details
```

- Delete category (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: deleteCategory with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource -->> User: DELETED Category
```

- Search category (only admin)

```mermaid
sequenceDiagram
  User ->> Keycloak: Authentication Request
  Keycloak -->> User: Bearer Token
  User ->> CategoryResource: searchCategories with bearer token
  CategoryResource ->> CategoryResource: Validate permission
  CategoryResource ->> Elasticsearch: Request data
  Elasticsearch -->> CategoryResource: Search response data
  CategoryResource -->> User: List categories
```

- ... The same for other APIs ...

## Technologie

- Framework: Jhipster
- Database: PostgreSQL
- Deployment: Heroku
