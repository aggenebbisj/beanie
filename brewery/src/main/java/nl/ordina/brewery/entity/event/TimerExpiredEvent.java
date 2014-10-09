package nl.ordina.brewery.entity.event;

import nl.ordina.brewery.entity.KettleEvent;

import javax.json.Json;
import javax.json.JsonObject;

public class TimerExpiredEvent implements KettleEvent {
  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("type", "TimerExpired")
        .build();
  }
  @Override
  public String toString() {
    return "TimerExpiredEvent{" +
        '}';
  }
}
