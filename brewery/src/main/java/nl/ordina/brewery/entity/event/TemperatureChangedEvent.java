package nl.ordina.brewery.entity.event;

import nl.ordina.brewery.entity.KettleEvent;
import nl.ordina.brewery.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.Serializable;

public class TemperatureChangedEvent implements KettleEvent, Serializable {
  private final Temperature goal;

  public TemperatureChangedEvent(Temperature goal) {
    this.goal = goal;
  }

  @Override
  public JsonObject createJson() {
    return
        Json.createObjectBuilder()
            .add("type", "TemperatureChanged")
            .add("temperature",
                Json.createObjectBuilder()
                    .add("scale", goal.getUnit().name())
                    .add("value", goal.getValue())
                    .build())
            .build();
  }

  public Temperature getGoal() {
    return goal;
  }

  @Override
  public String toString() {
    return "TemperatureChangedEvent{" +
        "goal reached=" + goal +
        '}';
  }
}
