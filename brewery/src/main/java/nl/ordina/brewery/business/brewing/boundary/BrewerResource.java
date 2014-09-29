package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.*;
import nl.ordina.brewery.business.brewing.entity.action.AddIngredient;
import nl.ordina.brewery.business.brewing.entity.action.ChangeTemperature;
import nl.ordina.brewery.business.brewing.entity.action.StableTemperature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import java.time.Duration;
import java.util.Arrays;

import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.valueOf;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;

@Path("brewer")
@ApplicationScoped
public class BrewerResource {
  @Inject Brewer brewer;

  @POST
  @Path("recipe")
  public void recipe(JsonObject obj) {
    System.out.println("RECIPE");
    Recipe recipe = new Recipe();
    Water water = new Water(new Volume(20, LITER));
    Malt malt = new Malt(new Volume(1, LITER));
    Temperature temperature = Temperature.ofDegrees(65, CELSIUS);
    Duration duration = Duration.ofMinutes(30);

    recipe.addStep("Mashing",
        Arrays.asList(water, malt),
        Arrays.asList(
            new AddIngredient(water),
            new ChangeTemperature(temperature),
            new AddIngredient(malt),
            new StableTemperature(duration)
        ));
    brewer.brew(recipe);

  }
}
