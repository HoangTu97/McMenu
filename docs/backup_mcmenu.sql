DELETE FROM public.rel_category__product; 
DELETE FROM public.rel_category__meal; 
DELETE FROM public.rel_product__ingredients; 
DELETE FROM public.rel_meal__product; 
DELETE FROM public.category; 
DELETE FROM public.ingredients; 
DELETE FROM public.nutrition_summary; 
DELETE FROM public.product; 
DELETE FROM public.meal; 











INSERT INTO public.category (id, name, image_url)VALUES (1, 'Breakfast', 'https://s7d1.scene7.com/is/image/mcdonalds/breakfast_300x300:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (2, 'Burgers', 'https://s7d1.scene7.com/is/image/mcdonalds/burgers_300x300:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (3, 'Chicken & Fish Sandwiches', 'https://s7d1.scene7.com/is/image/mcdonalds/nav_chickenfishsandwiches_160x160:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (4, 'McNuggets® and Meals', 'https://s7d1.scene7.com/is/image/mcdonalds/nav_mcnuggetscombo_160x160:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (5, 'Fries® & Sides', 'https://s7d1.scene7.com/is/image/mcdonalds/snacks_sides_300x300:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (6, 'Happy Meal®', 'https://s7d1.scene7.com/is/image/mcdonalds/nav_happy_meal_160x160:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (7, 'McCafé® Coffees', 'https://s7d1.scene7.com/is/image/mcdonalds/mccafe_300x300:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (8, 'McCafé® Bakery', 'https://s7d1.scene7.com/is/image/mcdonalds/Menu_LeftRail_mcd-160x160:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (9, 'Sweets & Treats', 'https://s7d1.scene7.com/is/image/mcdonalds/desserts_shakes_300x300:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (10, 'Beverages', 'https://s7d1.scene7.com/is/image/mcdonalds/drinks_300x300:category-panel-left-desktop');
INSERT INTO public.category (id, name, image_url)VALUES (11, '$1 $2 $3 Dollar Menu*', 'https://s7d1.scene7.com/is/image/mcdonalds/D123_160x160:category-panel-left-desktop');













INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (1, 'Biscuit', 'https://s7d1.scene7.com/is/image/mcdonalds/biscuit', 'Enriched Flour (bleached Wheat Flour, Niacin, Reduced Iron, Thiamin Mononitrate, Riboflavin, Folic Acid), Cultured Nonfat Buttermilk (cultured Skim Milk, Nonfat Dry Milk, Modified Food Starch, Salt, Mono And Diglycerides, Locust Bean Gum, Carrageenan), Palm Oil, Palm Kernel Oil, Water, Leavening (sodium Bicarbonate, Sodium Aluminum Phosphate, Monocalcium Phosphate), Contains 2% Or Less: Salt, Sugar, Modified Cellulose, Wheat Protein Isolate, Natural Flavor, Modified Food Starch, Xanthan Gum, Soy Lecithin.', 'Wheat, Milk, Soy.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (2, 'Folded Egg', 'https://s7d1.scene7.com/is/image/mcdonalds/folded_egg', 'Eggs, Nonfat Milk, Modified Food Starch, Salt, Citric Acid.', 'Egg, Milk.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (3, 'Pasteurized Process American Cheese', 'https://s7d1.scene7.com/is/image/mcdonalds/ingredient_american_cheese_180x180', 'Milk, Cream, Water, Sodium Citrate, Salt, Cheese Cultures, Citric Acid, Enzymes, Soy Lecithin, Color Added.', 'Milk, Soy.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (4, 'Thick Cut Applewood Smoked Bacon', 'https://s7d1.scene7.com/is/image/mcdonalds/applewood_bacon', 'Pork Bellies Cured With Water, Salt, Sugar, Natural Smoke Flavor, Sodium Phosphate, Sodium Erythorbate, Sodium Nitrite.', '');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (5, 'Salted Butter', 'https://s7d1.scene7.com/is/image/mcdonalds/butter_salted', 'Cream, Salt.', 'Milk.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (6, 'Clarified Butter', 'https://s7d1.scene7.com/is/image/mcdonalds/clarified_butter', 'Pasteurized Cream (butterfat).', 'Milk.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (7, 'English Muffin', 'https://s7d1.scene7.com/is/image/mcdonalds/english_muffin', 'Enriched Flour (Wheat Flour, Malted Barley Flour, Niacin, Iron, Thiamine, Riboflavin, Folic Acid), Water, Yeast, Yellow Corn Meal (Degermed Yellow Corn Meal and Corn Flour), Contains 2% or Less: Sugar, Soybean Oil, Salt, Dough Conditioners (Mono-, Di- And Tricalcium Phosphate, DATEM, Ascorbic Acid, Enzymes, Ethylated Mono and Digylcerides), Wheat Gluten, Cultured Wheat Flour, Citric Acid, Baking Soda, Fumaric Acid.', 'Wheat.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (8, 'Egg', 'https://s7d1.scene7.com/is/image/mcdonalds/round_egg', 'Usda Grade A Eggs.', 'Egg.');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (9, 'Canadian Bacon', 'https://s7d1.scene7.com/is/image/mcdonalds/canadian_bacon', 'Pork Cured With: Water, Sugar, Salt, Contains 2% Or Less: Sodium Lactate, Sodium Phosphate, Natural Flavor, Maltodextrin, Sodium Diacetate And Sodium Nitrite (preservatives).', '');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (10, 'Sausage Patty', 'https://s7d1.scene7.com/is/image/mcdonalds/sausage', 'Pork, Water, Salt, Spices, Dextrose, Sugar, Rosemary Extract, Natural Flavors.', '');
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (11, 'Griddle Cakes', 'https://s7d1.scene7.com/is/image/mcdonalds/McGriddles_top', 'Water, Enriched Flour (bleached Wheat Flour, Niacin, Iron, Thiamin Mononitrate, Riboflavin, Folic Acid), Sugar, Dextrose, Palm Oil, Contains 2% Or Less: Brown Sugar, Baking Powder (baking Soda, Sodium Acid Pyrophosphate, Monocalcium Phosphate), Modified Tapioca Starch, Rice Flour, Whey Powder, Salt, Natural Flavors, Buttermilk Powder, Soybean Oil, Caramel Color, Soy Lecithin.', 'Wheat, Milk, Soy.');

INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (101, 'Brewed Coffee', '', 'Brewed Coffee', NULL);
INSERT INTO public.ingredients (id, name, image_url, inside_ingredients, inside_contains)VALUES (201, 'Hash Browns', '', 'Potatoes, Vegetable Oil (canola Oil, Soybean Oil, Hydrogenated Soybean Oil, Natural Beef Flavor [wheat And Milk Derivatives]*), Salt, Corn Flour, Dehydrated Potato, Dextrose, Sodium Acid Pyrophosphate (maintain Color), Extractives Of Black Pepper. *natural Beef Flavor Contains Hydrolyzed Wheat And Hydrolyzed Milk As Starting Ingredients', 'Wheat, Milk.');



















INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (1, 'Bacon, Egg & Cheese Biscuit', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202211_0085_BaconEggCheeseBiscuit_832x472:product-header-mobile?wid=823&hei=472&dpr=off', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (2, 'Egg McMuffin®', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202004_0046_EggMcMuffin_832x472:product-header-desktop?wid=830&hei=458&dpr=off', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (3, 'Sausage McMuffin®', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202002_0078_SausageMcMuffin_832x472:product-header-desktop?wid=830&hei=458&dpr=off', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (4, 'Sausage McMuffin® with Egg', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202002_0078_SausageMcMuffin_832x472:product-header-desktop?wid=830&hei=458&dpr=off', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (5, 'Sausage Biscuit', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_201907_0062_SausageBiscuit_832x472:1-4-product-tile-desktop', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (6, 'Sausage Biscuit with Egg', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_201907_0092_SausageEggBiscuit_832x472:1-4-product-tile-desktop', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (7, 'Bacon, Egg & Cheese McGriddles®', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_201908_9839_BEC_McGriddle_832x472:1-4-product-tile-desktop', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (8, 'Sausage McGriddles®', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_201911_6110_SausageMcGriddle_832x472:1-4-product-tile-desktop', '', false);
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (9, 'Sausage, Egg & Cheese McGriddles®', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (10, 'Big Breakfast®', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (11, 'Big Breakfast® with Hotcakes', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (12, 'Hotcakes', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (13, 'Hotcakes and Sausage', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (14, 'Sausage Burrito', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (15, 'Hash Browns', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (16, 'Fruit & Maple Oatmeal', '', '', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (17, 'Big Mac®', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (18, 'Quarter Pounder®* with Cheese', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (19, 'Double Quarter Pounder®* with Cheese', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (20, 'McDouble®', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (21, 'Quarter Pounder®* with Cheese Bacon', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (22, 'Cheeseburger', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (23, 'Double Cheeseburger', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (24, 'Hamburger: The Classic McDonald''s Burger', '', '', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (25, 'Crispy Chicken Sandwich', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (26, 'Deluxe Crispy Chicken Sandwich', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (27, 'Spicy Crispy Chicken Sandwich', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (28, 'Spicy Deluxe Crispy Chicken Sandwich', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (29, 'Filet-O-Fish®', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (30, 'McChicken®', '', '', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (31, 'Chicken McNuggets®', '', '', false, 31, 1, '4 piece', '4', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (32, '6 Piece Chicken McNuggets®', '', '', false, 31, 2, '6 piece', '6', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (33, '10 Piece Chicken McNuggets®', '', '', false, 31, 3, '10 piece', '10', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (34, '20 Piece Chicken McNuggets®', '', '', false, 31, 4, '20 piece', '20', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (35, '40 Piece Chicken McNuggets®', '', '', false, 31, 5, '40 piece', '40', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (36, 'World Famous Fries®', '', '', false, 36, 1, 'Kids', 'K', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (37, 'World Famous Fries®', '', '', false, 36, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (38, 'World Famous Fries®', '', '', false, 36, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (39, 'World Famous Fries®', '', '', false, 36, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (40, 'Apple Slices', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (41, 'Tangy Barbeque Sauce', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (42, 'Spicy Buffalo Sauce', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (43, 'Honey Mustard Sauce', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (44, 'Honey', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (45, 'Sweet ''N Sour Sauce', '', '', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (46, 'Caramel Macchiato', '', '', false, 46, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (47, 'Caramel Macchiato', '', '', false, 46, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (48, 'Caramel Macchiato', '', '', false, 46, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (49, 'Cappuccino', '', '', false, 49, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (50, 'Cappuccino', '', '', false, 49, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (51, 'Cappuccino', '', '', false, 49, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (52, 'Caramel Cappuccino', '', '', false, 52, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (53, 'Caramel Cappuccino', '', '', false, 52, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (54, 'Caramel Cappuccino', '', '', false, 52, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (55, 'French Vanilla Cappuccino', '', '', false, 55, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (56, 'French Vanilla Cappuccino', '', '', false, 55, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (57, 'French Vanilla Cappuccino', '', '', false, 55, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (58, 'Mocha Latte', '', '', false, 58, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (59, 'Mocha Latte', '', '', false, 58, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (60, 'Mocha Latte', '', '', false, 58, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (61, 'Americano', '', '', false, 61, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (62, 'Americano', '', '', false, 61, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (63, 'Americano', '', '', false, 61, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (64, 'Premium Roast Coffee', '', '', false, 64, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (65, 'Premium Roast Coffee', '', '', false, 64, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (66, 'Premium Roast Coffee', '', '', false, 64, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (67, 'Iced Caramel Macchiato', '', '', false, 67, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (68, 'Iced Caramel Macchiato', '', '', false, 67, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (69, 'Iced Caramel Macchiato', '', '', false, 67, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (70, 'Iced Mocha', '', '', false, 70, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (71, 'Iced Mocha', '', '', false, 70, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (72, 'Iced Mocha', '', '', false, 70, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (73, 'Iced Coffee', '', '', false, 73, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (74, 'Iced Coffee', '', '', false, 73, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (75, 'Iced Coffee', '', '', false, 73, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (76, 'Iced Caramel Coffee', '', '', false, 76, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (77, 'Iced Caramel Coffee', '', '', false, 76, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (78, 'Iced Caramel Coffee', '', '', false, 76, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (79, 'Iced French Vanilla Coffee', '', '', false, 79, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (80, 'Iced French Vanilla Coffee', '', '', false, 79, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (81, 'Iced French Vanilla Coffee', '', '', false, 79, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (82, 'Latte', '', '', false, 82, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (83, 'Latte', '', '', false, 82, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (84, 'Latte', '', '', false, 82, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (85, 'Iced Latte', '', '', false, 85, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (86, 'Iced Latte', '', '', false, 85, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (87, 'Iced Latte', '', '', false, 85, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (88, 'Caramel Latte', '', '', false, 88, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (89, 'Caramel Latte', '', '', false, 88, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (90, 'Caramel Latte', '', '', false, 88, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (91, 'Iced Caramel Latte', '', '', false, 91, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (92, 'Iced Caramel Latte', '', '', false, 91, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (93, 'Iced Caramel Latte', '', '', false, 91, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (94, 'French Vanilla Latte', '', '', false, 94, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (95, 'French Vanilla Latte', '', '', false, 94, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (96, 'French Vanilla Latte', '', '', false, 94, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (97, 'Iced French Vanilla Latte', '', '', false, 97, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (98, 'Iced French Vanilla Latte', '', '', false, 97, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (99, 'Iced French Vanilla Latte', '', '', false, 97, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (100, 'Caramel Frappé', '', '', false, 100, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (101, 'Caramel Frappé', '', '', false, 100, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (102, 'Caramel Frappé', '', '', false, 100, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (103, 'Mocha Frappé', '', '', false, 103, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (104, 'Mocha Frappé', '', '', false, 103, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (105, 'Mocha Frappé', '', '', false, 103, 3, 'Large', 'L', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (106, 'Apple Fritter', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (107, 'Blueberry Muffin', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (108, 'Cinnamon Roll with Cream Cheese Icing', '', '', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (109, 'Shamrock Shake®', '', '', true, 109, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (110, 'Shamrock Shake®', '', '', true, 109, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (111, 'Shamrock Shake®', '', '', true, 109, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (112, 'OREO® Shamrock McFlurry®', '', '', true, 112, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (113, 'OREO® Shamrock McFlurry®', '', '', true, 112, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (114, 'OREO® Shamrock McFlurry®', '', '', true, 112, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (115, 'McFlurry® with OREO® Cookies', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (116, 'McFlurry® with M&M''S® Candies', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (117, 'Vanilla Cone', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (118, 'Chocolate Shake', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (119, 'Vanilla Shake', '', '', false, 119, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (120, 'Vanilla Shake', '', '', false, 119, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (121, 'Vanilla Shake', '', '', false, 119, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (122, 'Strawberry Shake', '', '', false, 122, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (123, 'Strawberry Shake', '', '', false, 122, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (124, 'Strawberry Shake', '', '', false, 122, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (125, 'Hot Fudge Sundae', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (126, 'Hot Caramel Sundae', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (127, 'Baked Apple Pie', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (128, 'Chocolate Chip Cookie', '', '', false); 

INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (129, 'Coca-Cola®', '', '', false, 129, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (130, 'Coca-Cola®', '', '', false, 129, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (131, 'Coca-Cola®', '', '', false, 129, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (132, 'Coca-Cola®', '', '', false, 129, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (133, 'Sprite®', '', '', false, 133, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (134, 'Sprite®', '', '', false, 133, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (135, 'Sprite®', '', '', false, 133, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (136, 'Sprite®', '', '', false, 133, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (137, 'Dr Pepper®', '', '', false, 137, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (138, 'Dr Pepper®', '', '', false, 137, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (139, 'Dr Pepper®', '', '', false, 137, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (140, 'Dr Pepper®', '', '', false, 137, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (141, 'Fanta® Orange', '', '', false, 141, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (142, 'Fanta® Orange', '', '', false, 141, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (143, 'Fanta® Orange', '', '', false, 141, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (144, 'Diet Coke®', '', '', false, 144, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (145, 'Diet Coke®', '', '', false, 144, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (146, 'Diet Coke®', '', '', false, 144, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (147, 'Diet Coke®', '', '', false, 144, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (148, 'Hi-C® Orange Lavaburst®', '', '', false, 148, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (149, 'Hi-C® Orange Lavaburst®', '', '', false, 148, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (150, 'Hi-C® Orange Lavaburst®', '', '', false, 148, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (151, 'Hi-C® Orange Lavaburst®', '', '', false, 148, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (152, 'Strawberry Banana Smoothie', '', '', false, 152, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (153, 'Strawberry Banana Smoothie', '', '', false, 152, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (154, 'Strawberry Banana Smoothie', '', '', false, 152, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (155, 'Mango Pineapple Smoothie', '', '', false, 155, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (156, 'Mango Pineapple Smoothie', '', '', false, 155, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (157, 'Mango Pineapple Smoothie', '', '', false, 155, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (158, 'Sweet Tea', '', '', false, 158, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (159, 'Sweet Tea', '', '', false, 158, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (160, 'Sweet Tea', '', '', false, 158, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (161, 'Sweet Tea', '', '', false, 158, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (162, 'Unsweetened Iced Tea', '', '', false, 162, 1, 'Extra Small', 'XS', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (163, 'Unsweetened Iced Tea', '', '', false, 162, 2, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (164, 'Unsweetened Iced Tea', '', '', false, 162, 3, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (165, 'Unsweetened Iced Tea', '', '', false, 162, 4, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (166, 'Hot Tea', '', '', false, 166, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (167, 'Hot Tea', '', '', false, 166, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (168, 'Hot Tea', '', '', false, 166, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (169, 'Minute Maid® Premium Orange Juice', '', '', false, 169, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (170, 'Minute Maid® Premium Orange Juice', '', '', false, 169, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (171, 'Minute Maid® Premium Orange Juice', '', '', false, 169, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (172, 'Honest Kids® Appley Ever After® Organic Juice Drink', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (173, '1% Low Fat Milk Jug', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (174, 'Reduced Sugar* Low Fat Chocolate Milk Jug', '', '', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (175, 'Hot Chocolate', '', '', false, 175, 1, 'Small', 'S', true); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (176, 'Hot Chocolate', '', '', false, 175, 2, 'Medium', 'M', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only, related_product_id, display_order, label, abbr_label, is_default)VALUES (177, 'Hot Chocolate', '', '', false, 175, 3, 'Large', 'L', false); 
INSERT INTO public.product (id, name, image_url, description, is_limited_time_only)VALUES (178, 'DASANI® Water', '', '', false); 



















INSERT INTO public.meal (id, name, image_url)VALUES (1, 'Egg McMuffin® Meal', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202007_0252_EVM_HB_EggMcMuffin_Coffee_Glass_832x472:product-header-desktop?wid=830&hei=458&dpr=off'); 
INSERT INTO public.meal (id, name, image_url)VALUES (2, 'Sausage McMuffin® with Egg Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (3, 'Sausage Biscuit with Egg Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (4, 'Bacon, Egg & Cheese Biscuit Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (5, 'Bacon, Egg & Cheese McGriddles® Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (6, 'Sausage, Egg & Cheese McGriddles® Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (7, 'Sausage McGriddles® Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (8, 'Sausage Burrito Meal', ''); 

INSERT INTO public.meal (id, name, image_url)VALUES (9, 'Hamburger Happy Meal®', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (10, '4 Piece Chicken McNuggets® Happy Meal', ''); 
INSERT INTO public.meal (id, name, image_url)VALUES (11, '6 Piece Chicken McNuggets® Happy Meal', ''); 



















INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (1, 'CALORIES', 460, NULL, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (2, 'TOTAL_FAT', 26000, 34, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (3, 'TOTAL_CARBS', 39000, 14, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (4, 'PROTEIN', 17000, NULL, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (5, 'SATUREATED_FAT', 13000, 66, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (6, 'DIETARY_FIBER', 2000, 6, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (7, 'CALCIUM', 180, 15, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (8, 'TRANS_FAT', 0, NULL, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (9, 'TOTAL_SUGARS', 3000, NULL, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (10, 'ADDED_SUGARS', 1000, 2, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (11, 'IRON', 3, 15, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (12, 'CHOLESTEROL', 215, 72, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (13, 'VITAMIN_D', 0, 6, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (14, 'POTASSIUM', 240, 6, 1); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (15, 'SODIUM', 1330, 58, 1); 

INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (16, 'CALORIES', 310, NULL, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (17, 'TOTAL_FAT', 13000, 17, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (18, 'TOTAL_CARBS', 30000, 11, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (19, 'PROTEIN', 17000, NULL, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (20, 'SATUREATED_FAT', 6000, 31, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (21, 'DIETARY_FIBER', 2000, 6, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (22, 'CALCIUM', 170, 15, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (23, 'TRANS_FAT', 0, NULL, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (24, 'TOTAL_SUGARS', 3000, NULL, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (25, 'ADDED_SUGARS', 1000, 2, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (26, 'IRON', 3, 15, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (27, 'CHOLESTEROL', 250, 83, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (28, 'VITAMIN_D', 2, 15, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (29, 'POTASSIUM', 200, 4, 2); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (30, 'SODIUM', 770, 33, 2); 

INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (31, 'CALORIES', 400, NULL, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (32, 'TOTAL_FAT', 26000, 33, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (33, 'TOTAL_CARBS', 29000, 11, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (34, 'PROTEIN', 14000, NULL, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (35, 'SATUREATED_FAT', 10000, 51, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (36, 'DIETARY_FIBER', 2000, 7, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (37, 'CALCIUM', 140, 10, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (38, 'TRANS_FAT', 0, NULL, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (39, 'TOTAL_SUGARS', 2000, NULL, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (40, 'ADDED_SUGARS', 1000, 1, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (41, 'IRON', 25, 15, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (42, 'CHOLESTEROL', 55, 18, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (43, 'VITAMIN_D', 0, 4, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (44, 'POTASSIUM', 190, 4, 3); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (45, 'SODIUM', 760, 33, 3); 

INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (131, 'CALORIES', 140, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (132, 'TOTAL_FAT', 8000, 10, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (133, 'TOTAL_CARBS', 18, 6, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (134, 'PROTEIN', 2000, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (135, 'SATUREATED_FAT', 1000, 5, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (136, 'DIETARY_FIBER', 2000, 6, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (137, 'CALCIUM', 8, 6, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (138, 'TRANS_FAT', NULL, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (139, 'TOTAL_SUGARS', NULL, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (140, 'ADDED_SUGARS', NULL, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (141, 'IRON', 5, 2, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (142, 'CHOLESTEROL', NULL, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (143, 'VITAMIN_D', NULL, NULL, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (144, 'POTASSIUM', 240, 6, 15); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (145, 'SODIUM', 310, 14, 15); 

INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (231, 'CALORIES', 5, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (232, 'TOTAL_FAT', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (233, 'TOTAL_CARBS', 1000, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (234, 'PROTEIN', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (235, 'SATUREATED_FAT', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (236, 'DIETARY_FIBER', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (237, 'CALCIUM', 6, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (238, 'TRANS_FAT', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (239, 'TOTAL_SUGARS', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (240, 'ADDED_SUGARS', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (241, 'IRON', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (242, 'CHOLESTEROL', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (243, 'VITAMIN_D', NULL, NULL, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (244, 'POTASSIUM', 160, 4, 64); 
INSERT INTO public.nutrition_summary (id, key, quantity_mg, percent_daily_values, product_id)VALUES (245, 'SODIUM', 10, NULL, 64); 


















INSERT INTO public.rel_category__product (product_id, category_id)VALUES (1, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (2, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (3, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (4, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (5, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (6, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (7, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (8, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (9, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (10, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (11, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (12, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (13, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (14, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (15, 1); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (16, 1); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (17, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (18, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (19, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (20, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (21, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (22, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (23, 2); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (24, 2); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (25, 3); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (26, 3); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (27, 3); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (28, 3); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (29, 3); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (30, 3); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (31, 4); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (33, 4); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (37, 5); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (40, 5); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (41, 5); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (42, 5); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (43, 5); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (44, 5); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (45, 5); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (46, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (49, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (52, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (55, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (58, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (61, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (64, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (67, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (70, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (73, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (76, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (79, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (82, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (85, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (88, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (91, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (94, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (97, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (100, 7); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (103, 7); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (106, 8); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (107, 8); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (108, 8); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (109, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (112, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (115, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (116, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (117, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (118, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (119, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (122, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (125, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (126, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (127, 9); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (128, 9); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (130, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (133, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (138, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (141, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (145, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (149, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (152, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (155, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (159, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (163, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (166, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (169, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (172, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (173, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (174, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (175, 10); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (178, 10); 

INSERT INTO public.rel_category__product (product_id, category_id)VALUES (5, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (3, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (8, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (15, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (20, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (30, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (31, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (37, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (130, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (134, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (141, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (138, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (145, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (149, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (163, 11); 
INSERT INTO public.rel_category__product (product_id, category_id)VALUES (159, 11); 

















INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (1, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (2, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (3, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (4, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (5, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (6, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (7, 1); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (8, 1); 

INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (9, 6); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (10, 6); 
INSERT INTO public.rel_category__meal (meal_id, category_id)VALUES (11, 6); 

















INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (1, 1); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (2, 1); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (3, 1); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (4, 1); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (5, 1); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (6, 1); 

INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (7, 2); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (8, 2); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (9, 2); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (3, 2); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (5, 2); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (6, 2); 

INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (7, 3); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (10, 3); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (3, 3); 
INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (5, 3); 

INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (201, 15); 

INSERT INTO public.rel_product__ingredients (ingredients_id, product_id)VALUES (101, 64); 



















INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (2, 1); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 1); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 1); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (4, 2); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 2); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 2); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (6, 3); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 3); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 3); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (1, 4); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 4); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 4); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (7, 5); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 5); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 5); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (9, 6); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 6); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 6); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (8, 7); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 7); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 7); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (14, 8); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (64, 8); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (15, 8); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (24, 9); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (36, 9); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (173, 9); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (40, 9); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (31, 10); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (36, 10); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (173, 10); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (40, 10); 

INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (32, 11); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (36, 11); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (173, 11); 
INSERT INTO public.rel_meal__product (product_id, meal_id)VALUES (40, 11); 



