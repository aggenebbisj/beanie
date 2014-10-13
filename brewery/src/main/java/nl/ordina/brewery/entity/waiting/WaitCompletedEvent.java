package nl.ordina.brewery.entity.waiting;

import nl.ordina.brewery.entity.ActionEvent;

import javax.json.Json;
import javax.json.JsonObject;

public class WaitCompletedEvent implements ActionEvent {
  
  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("event", "waiting completed")
        .build();
  }
  
  @Override
  public String toString() {
    return "WaitCompletedEvent{}";
  }
}
