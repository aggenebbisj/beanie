package nl.ordina.brewery.manual.boundary;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.temperature.Temperature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import nl.ordina.brewery.entity.Manual;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.valueOf;

@Path("kettle")
@ApplicationScoped
public class ManualBrewingResource {

  @Inject @Manual
  Kettle kettle;
 
  @PUT
  @Path("ingredient")
  @Consumes(APPLICATION_JSON)
  public void post(Ingredient ingredient) {
    kettle.addIngredient(ingredient);
  }

  @POST
  @Path("temperature")
  public void changeTemperature(JsonObject obj) {
    JsonObject goal = obj.getJsonObject("goal");
    kettle.changeTemperatureTo(new Temperature(goal.getInt("value"), valueOf(goal.getString("unit"))));
  }
}
