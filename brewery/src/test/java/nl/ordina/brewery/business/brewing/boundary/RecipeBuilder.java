package nl.ordina.brewery.business.brewing.boundary;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import java.time.Duration;

import static javax.json.Json.createObjectBuilder;

public class RecipeBuilder {
  public static JsonObjectBuilder createIngredient(String type, int amount) {
    return createObjectBuilder()
        .add("type", type)
        .add("volume", createObjectBuilder()
            .add("amount", amount)
            .add("unit", "LITER"));
  }

  public static JsonObjectBuilder createAddIngredientAction(JsonObjectBuilder ingredient) {
    return createObjectBuilder()
        .add("type", "AddIngredient")
        .add("ingredient", ingredient);
  }

  public static JsonObjectBuilder createChangeTemperature(int inCelsius) {
    return createObjectBuilder()
        .add("type", "ChangeTemperature")
        .add("value", inCelsius)
        .add("unit", "CELSIUS");
  }

  public static JsonObjectBuilder createStableTemperature(Duration duration) {
    return createObjectBuilder()
        .add("type", "StableTemperature")
        .add("duration", duration.toString());
  }

  public static JsonObjectBuilder createStep(String name, JsonArrayBuilder actions) {
    return createObjectBuilder()
        .add("name", name)
        .add("actions", actions);
  }
}
