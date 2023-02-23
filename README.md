# McMenu

This application was generated using JHipster 7.9.3

## Database schema

![Database schema](./docs/database_schema.png)

## Sequence Diagram

1. Login

```mermaid
sequenceDiagram
    User ->> Keycloak: Authentication Request
    Keycloak -->> User: Login Page
    User ->> User: Enter Login Details
    User ->> Keycloak: Submit Login
    Keycloak ->> Keycloak: Validate
    Keycoak --> User: Redirect to Application
```

2. Load full menu

```mermaid
sequenceDiagram
  User ->> Public_Category_Resource: getAllCategories
  Public_Category_Resource -->> User: List categories
  User ->> Public_Category_Resource: getCategoryFullMenu (for each category)
  Public_Category_Resource -->> User: List products and meals of each category
```

3. Product details

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

## Technologie

- Framework: Jhipster
- Database: PostgreSQL
- Deployment: Heroku
