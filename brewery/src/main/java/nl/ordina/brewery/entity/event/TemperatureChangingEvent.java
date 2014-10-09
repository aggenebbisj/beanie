package nl.ordina.brewery.entity.event;

import nl.ordina.brewery.entity.MonitorableEvent;
import nl.ordina.brewery.entity.StandardKettle;
import nl.ordina.brewery.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;

public class TemperatureChangingEvent implements MonitorableEvent {
  private final StandardKettle kettle;
  private final Temperature goal;

  public TemperatureChangingEvent(StandardKettle kettle, Temperature goal) {
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

  public StandardKettle getKettle() {
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
