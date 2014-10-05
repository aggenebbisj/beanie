package nl.ordina.brewery.business.brewing.entity.event;

import nl.ordina.brewery.business.brewing.entity.KettleEvent;
import nl.ordina.brewery.business.brewing.entity.StandardKettle;
import nl.ordina.brewery.business.brewing.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;

public class TemperatureChangingEvent implements KettleEvent {
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
            .add("type", "TemperatureChanging")
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
