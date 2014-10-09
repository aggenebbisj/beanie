package nl.ordina.brewery.entity.event;

import nl.ordina.brewery.entity.MonitorableEvent;

import javax.json.JsonObject;

import static javax.json.Json.createObjectBuilder;

public class RecipeCompleteEvent implements MonitorableEvent{
  @Override
  public JsonObject createJson() {
    return createObjectBuilder()
        .add("event", "recipe completed")
        .build();
  }
}
