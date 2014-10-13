package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.MonitoringEvent;

import javax.json.JsonObject;

import static javax.json.Json.createObjectBuilder;

public class RecipeCompletedEvent implements MonitoringEvent {
  @Override
  public JsonObject createJson() {
    return createObjectBuilder()
        .add("event", "recipe completed")
        .build();
  }
}
