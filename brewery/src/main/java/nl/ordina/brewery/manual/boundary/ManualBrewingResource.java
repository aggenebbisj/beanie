package nl.ordina.brewery.manual.boundary;

import nl.ordina.brewery.boundary.IngredientParser;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.producer.Manual;
import nl.ordina.brewery.entity.temperature.Temperature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.time.Duration;

import static javax.json.Json.createObjectBuilder;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.valueOf;

@Path("kettle")
@ApplicationScoped
public class ManualBrewingResource {

  @Inject
  @Manual
  Kettle kettle;
  @Inject
  IngredientParser parser;

  @POST
  @Path("ingredients")
  @Consumes(APPLICATION_JSON)
  public void post(JsonObject obj) {
    kettle.addIngredient(parser.parseIngredient(obj));
  }

  @POST
  @Path("temperature")
  public void changeTemperature(JsonObject obj) {
    JsonObject goal = obj.getJsonObject("goal");
    final Temperature newTemperature = new Temperature(goal.getInt("value"), valueOf(goal.getString("unit")));
    kettle.changeTemperatureTo(newTemperature);
  }

  @GET
  @Path("temperature")
  public JsonObject getTemperature() {
    final Temperature current = kettle.getTemperature();
    return createObjectBuilder().add(
        "temperature", createObjectBuilder()
            .add("value", current.getValue())
            .add("unit", current.getUnit().name()).build()
    ).build();
  }


  @POST
  @Path("steady")
  public void keepSteadyTemperature(JsonObject obj) {
    final Duration duration = Duration.parse(obj.getString("duration"));
    kettle.lock(duration);
  }

}
