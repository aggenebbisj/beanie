package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.time.Duration;
import java.util.Arrays;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.valueOf;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;

@Path("kettle")
@ApplicationScoped
public class KettleResource {

  @Inject
  Kettle kettle;

  @PUT
  @Path("ingredient")
  @Consumes(APPLICATION_JSON)
  public void post(JsonObject obj) {
    System.out.println("Be there");
    final String type = obj.getString("type");
    final JsonObject volume = obj.getJsonObject("volume");
    final Ingredient ingredient;
    switch (type) {
      case "water": {
        ingredient = new Water(new Volume(volume.getInt("amount"), Volume.VolumeUnit.valueOf(volume.getString("unit"))));
        break;
      }
      case "malt": {
        ingredient = new Malt(new Volume(volume.getInt("amount"), Volume.VolumeUnit.valueOf(volume.getString("unit"))));
        break;
      }
      default:
        System.out.println("Unknown ingredient: " + type);
        throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
    kettle.addIngredient(ingredient);
  }

  @POST
  @Path("temperature")
  public void changeTemperature(JsonObject obj) {
    if (obj.get("goal") == null) {
      System.out.println("Duration: " + obj.getString("duration"));
      java.time.Duration duration = Duration.parse(obj.getString("duration"));
      kettle.keepStableTemperature(duration);
    } else {
      JsonObject goal = obj.getJsonObject("goal");
      kettle.changeTemperatureTo(new Temperature(goal.getInt("value"), valueOf(goal.getString("unit"))));
    }
  }
}
