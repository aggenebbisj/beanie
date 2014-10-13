package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.MonitoringEvent;

import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.brewery.entity.Kettle;

public class TemperatureChangingEvent implements MonitoringEvent {
  private final Kettle kettle;
  private final Temperature goal;

  public TemperatureChangingEvent(Kettle kettle, Temperature goal) {
    this.kettle = kettle;
    this.goal = goal;
  }

  @Override
  public JsonObject createJson() {
    return
        Json.createObjectBuilder()
            .add("event", "temperature changing")
            .add("kettle",
                Json.createObjectBuilder()
                    .add("scale", kettle.getTemperature().getUnit().name())
                    .add("value", kettle.getTemperature().getValue())
                    .build())
            .add("goal",
                Json.createObjectBuilder()
                    .add("scale", goal.getUnit().name())
                    .add("value", goal.getValue())
                    .build())
            .build();
  }

  public Kettle getKettle() {
    return kettle;
  }

  public Temperature getGoal() {
    return goal;
  }

  @Override
  public String toString() {
    return "TemperatureChangingEvent{" +
        "current=" + kettle.getTemperature() +
        ", goal=" + goal +
        '}';
  }
}
