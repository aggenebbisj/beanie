package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.ActionableEvent;
import nl.ordina.brewery.entity.temperature.Temperature;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.Serializable;

public class TemperatureReachedEvent implements ActionableEvent, Serializable {
  private final Temperature goal;

  public TemperatureReachedEvent(Temperature goal) {
    this.goal = goal;
  }

  @Override
  public JsonObject createJson() {
    return
        Json.createObjectBuilder()
            .add("event", "temperature reached goal")
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
    return "TemperatureReachedEvent{" +
        "goal reached=" + goal +
        '}';
  }
}
