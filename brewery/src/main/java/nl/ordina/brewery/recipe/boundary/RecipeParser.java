package nl.ordina.brewery.recipe.boundary;

import nl.ordina.brewery.boundary.IngredientParser;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.*;
import nl.ordina.brewery.entity.ingredient.AddIngredientAction;
import nl.ordina.brewery.entity.temperature.ChangeTemperatureAction;
import nl.ordina.brewery.entity.waiting.WaitAction;
import nl.ordina.brewery.recipe.entity.Recipe;
import nl.ordina.brewery.recipe.entity.Step;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import java.time.Duration;
import java.util.List;

import static java.util.stream.Collectors.toList;
import javax.inject.Inject;
import javax.json.JsonValue;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

public class RecipeParser {

    @Inject
    IngredientParser ingredientParser;

    public Recipe parseRecipe(JsonObject obj) {
//    final List<Step> steps =
//        obj.getJsonArray("steps").stream()
//            .map(j -> (JsonObject) j)
//            .map(this::mapStep)
//            .collect(toList());
//    return new Recipe(steps);

        List<KettleAction> steps
                = obj.getJsonArray("steps").stream()
                .map(j -> (JsonObject) j)
                .map(this::mapAction)
                .collect(toList());
        return new Recipe(obj.getString("name"), steps);
    }

    private Step mapStep(JsonObject j) {
        return new Step(j.getString("name"),
                getActions(j.getJsonArray("actions")));
    }

    private List<KettleAction> getActions(JsonArray actions) {
        return actions.stream()
                .map(a -> (JsonObject) a)
                .map(this::mapAction)
                .collect(toList());
    }

    private KettleAction mapAction(JsonObject j) {
        System.out.println("mapAction: " + j);
        switch (j.getString("type")) {
            case "AddIngredient":
                return new AddIngredientAction(ingredientParser.parseIngredient(j.getJsonObject("ingredient")));
            case "ChangeTemperature":
                return new ChangeTemperatureAction(new Temperature(j.getJsonObject("temperature").getInt("value"), Temperature.TemperatureUnit.valueOf(j.getJsonObject("temperature").getString("unit"))));
            case "StableTemperature":
                return new WaitAction(Duration.parse(j.getString("duration")));
            default:
                throw new WebApplicationException(NOT_FOUND);
        }
    }
}
