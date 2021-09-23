package com.example.codeclan.server;

import com.example.codeclan.server.apis.MealAPI;
import com.example.codeclan.server.models.Cocktail;
import com.example.codeclan.server.models.CocktailRecipePayload;
import com.example.codeclan.server.models.Meal;
import com.example.codeclan.server.models.MealRecipePayload;
import com.example.codeclan.server.services.Converter;
import com.example.codeclan.server.services.InputFormatter;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConverterTest {
    @Autowired
    MealAPI mealAPI;

    @Test
    public void canConvertJsonNodeToMealPayload() throws IOException {
        JsonNode meal = mealAPI.getRecipe("52772");
        Converter converter = new Converter();
        Object result = converter.convertMealJsonNodeToRecipePayload(meal);
        assertThat(result, instanceOf(MealRecipePayload.class));
    }

    @Test public void canConvertJsonNodeToCocktailPayload() throws IOException {
        JsonNode cocktail = mealAPI.getCocktail("11007");
        Converter converter = new Converter();
        Object result = converter.convertJsonNodeToCocktailRecipePayload(cocktail);
        assertThat(result, instanceOf(CocktailRecipePayload.class));
    }

    @Test
    public void canConvertListofMealPayloadsToMeals() throws IOException, NoSuchFieldException, IllegalAccessException {
        List<MealRecipePayload> mealRecipePayloads = getMealRecipePayloadsToTest("chicken, onion");
        Converter converter = new Converter();
        List<Meal> meals = converter.convertMealRecipePayloadsToMeals(mealRecipePayloads);
        assertThat(meals.get(0), instanceOf(Meal.class));
        assertEquals(3, meals.size());
    }

    @Test
    public void canConvertIndividualMealPayloadtoMeal() throws IOException, NoSuchFieldException, IllegalAccessException {
        Converter converter = new Converter();
        MealRecipePayload testRecipe = getIndividualMealRecipePayloadToTest("chicken, onion");
        Object result = converter.convertIndividualMealRecipePayloadToMeal(testRecipe);
        assertThat(result, instanceOf(Meal.class));
    }

    @Test
    public void canConvertListOfCocktailPayloadsToCocktails() throws IOException, NoSuchFieldException, IllegalAccessException {
        List<CocktailRecipePayload> cocktailRecipePayloads = getCocktailRecipePayloadsToTest("gin, vodka");
        Converter converter = new Converter();
        List<Cocktail> cocktails = converter.convertCocktailRecipePayloadsToCocktails(cocktailRecipePayloads);
        assertThat(cocktails.get(0), instanceOf(Cocktail.class));
    }

    public List<MealRecipePayload> getMealRecipePayloadsToTest(String ingredients) throws IOException {
        InputFormatter inputFormatter = new InputFormatter();
        Converter converter = new Converter();
        String formattedMealIngredients = inputFormatter.formatInput(ingredients);
        ArrayList<MealRecipePayload> mealRecipes = new ArrayList<>();

        JsonNode foundMeals = mealAPI.getMeals(formattedMealIngredients);

        for (JsonNode node:foundMeals) {
            JsonNode recipeNode = node.get("idMeal");
            int recipeId = recipeNode.asInt();
            mealRecipes.add(converter.convertMealJsonNodeToRecipePayload(mealAPI.getRecipe(Integer.toString(recipeId))));
        }

        return mealRecipes;
    }

    private List<CocktailRecipePayload> getCocktailRecipePayloadsToTest(String ingredients) throws IOException {
        InputFormatter inputFormatter = new InputFormatter();
        Converter converter = new Converter();
        String formattedCocktailIngredients = inputFormatter.formatInput(ingredients);
        ArrayList<CocktailRecipePayload> cocktailRecipes = new ArrayList<>();

        JsonNode foundCocktails = mealAPI.getCocktails(formattedCocktailIngredients);

        for (JsonNode node:foundCocktails) {
            JsonNode recipeNode = node.get("idDrink");
            int recipeId = recipeNode.asInt();
            cocktailRecipes.add(converter.convertJsonNodeToCocktailRecipePayload(mealAPI.getCocktail(Integer.toString(recipeId))));
        }

        return cocktailRecipes;
    }

    private MealRecipePayload getIndividualMealRecipePayloadToTest(String ingredients) throws IOException {
        InputFormatter inputFormatter = new InputFormatter();
        Converter converter = new Converter();
        String formattedMealIngredients = inputFormatter.formatInput(ingredients);
        ArrayList<MealRecipePayload> mealRecipes = new ArrayList<>();

        JsonNode foundMeals = mealAPI.getMeals(formattedMealIngredients);

        for (JsonNode node:foundMeals) {
            JsonNode recipeNode = node.get("idMeal");
            int recipeId = recipeNode.asInt();
            mealRecipes.add(converter.convertMealJsonNodeToRecipePayload(mealAPI.getRecipe(Integer.toString(recipeId))));
        }

        MealRecipePayload testRecipe = mealRecipes.get(0);

        return testRecipe;
    }

    private CocktailRecipePayload getIndividualCocktailRecipePayloadToTest(String ingredients) throws IOException {
        InputFormatter inputFormatter = new InputFormatter();
        Converter converter = new Converter();
        String formattedCocktailIngredients = inputFormatter.formatInput(ingredients);
        ArrayList<CocktailRecipePayload> cocktailRecipes = new ArrayList<>();

        JsonNode foundCocktails = mealAPI.getCocktails(formattedCocktailIngredients);

        for (JsonNode node:foundCocktails) {
            JsonNode recipeNode = node.get("idDrink");
            int recipeId = recipeNode.asInt();
            cocktailRecipes.add(converter.convertJsonNodeToCocktailRecipePayload(mealAPI.getCocktail(Integer.toString(recipeId))));
        }

        CocktailRecipePayload testCocktail = cocktailRecipes.get(0);

        return testCocktail;
    }
}
