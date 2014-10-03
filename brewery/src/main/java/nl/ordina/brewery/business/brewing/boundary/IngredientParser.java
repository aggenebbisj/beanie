package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.Ingredient;
import nl.ordina.brewery.business.brewing.entity.Malt;
import nl.ordina.brewery.business.brewing.entity.Volume;
import nl.ordina.brewery.business.brewing.entity.Water;

import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.valueOf;

public class IngredientParser {
  public static Ingredient parseIngredient(JsonObject obj) {
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
