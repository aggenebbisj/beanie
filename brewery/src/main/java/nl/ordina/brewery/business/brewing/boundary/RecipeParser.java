package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.*;
import nl.ordina.brewery.business.brewing.entity.action.AddIngredient;
import nl.ordina.brewery.business.brewing.entity.action.ChangeTemperature;
import nl.ordina.brewery.business.brewing.entity.action.StableTemperature;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.valueOf;

public class RecipeParser {
  public Recipe parseRecipe(JsonObject obj) {
    final List<Step> steps =
        obj.getJsonArray("steps").stream()
            .map(j -> (JsonObject) j)
            .map(this::mapStep)
            .collect(toList());
    return new Recipe(steps);
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
    switch (j.getString("type")) {
      case "AddIngredient" : return new AddIngredient(parseIngredient(j.getJsonObject("ingredient")));
      case "ChangeTemperature" : return new ChangeTemperature(new Temperature(j.getInt("value"), Temperature.TemperatureUnit.valueOf(j.getString("unit"))));
      case "StableTemperature": return new StableTemperature(Duration.parse(j.getString("duration")));
      default : throw new WebApplicationException(NOT_FOUND);
    }
  }

  Ingredient parseIngredient(JsonObject obj) {
    Volume volume = parseVolume(obj.getJsonObject("volume"));

    switch (obj.getString("type")) {
      case "water": {
        return new Water(volume);
      }
      case "malt": {
        return new Malt(volume);
      }
      default:
        System.out.println("Unknown ingredient: " + obj.getString("type"));
        throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  private static Volume parseVolume(JsonObject jsonVolume) {
    final int amount = jsonVolume.getInt("amount");
    final String unit = jsonVolume.getString("unit");
    return new Volume(amount, valueOf(unit));
  }
}
