package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.ActionableEvent;

import javax.json.Json;
import javax.json.JsonObject;

public class TimerExpiredEvent implements ActionableEvent {
  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("event", "kitchen timer rings")
        .build();
  }
  @Override
  public String toString() {
    return "TimerExpiredEvent{" +
        '}';
  }
}
