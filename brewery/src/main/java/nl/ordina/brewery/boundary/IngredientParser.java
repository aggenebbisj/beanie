
package nl.ordina.brewery.boundary;

import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import nl.ordina.brewery.entity.capacity.Volume;
import static nl.ordina.brewery.entity.capacity.Volume.VolumeUnit.valueOf;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.Malt;
import nl.ordina.brewery.entity.ingredient.Water;

public class IngredientParser {
    public Ingredient parseIngredient(JsonObject obj) {
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
