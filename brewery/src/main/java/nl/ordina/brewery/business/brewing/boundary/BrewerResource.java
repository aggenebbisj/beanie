package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.*;
import nl.ordina.brewery.business.brewing.entity.action.AddIngredient;
import nl.ordina.brewery.business.brewing.entity.action.ChangeTemperature;
import nl.ordina.brewery.business.brewing.entity.action.StableTemperature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static nl.ordina.brewery.business.brewing.boundary.IngredientParser.parseIngredient;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.valueOf;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;

@Path("brewer")
@ApplicationScoped
public class BrewerResource {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @Inject
  Brewer brewer;

  @POST
  @Path("recipe")
  public void recipe(JsonObject obj) {
    log.log(Level.INFO, "Starting a new recipe with {0}", obj);

    final List<Step> steps =
        obj.getJsonArray("steps").stream()
            .map(j -> (JsonObject) j)
            .map(this::mapStep)
            .collect(toList());
    Recipe newRecipe = new Recipe(steps);
    brewer.brew(newRecipe);

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
      case "ChangeTemperature" : return new ChangeTemperature(new Temperature(j.getInt("value"), valueOf(j.getString("unit"))));
      case "StableTemperature": return new StableTemperature(Duration.parse(j.getString("duration")));
      default : throw new WebApplicationException(NOT_FOUND);
    }
  }
}
