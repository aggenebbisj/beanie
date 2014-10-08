package nl.ordina.brewery.business.brewing.entity.event;

import nl.ordina.brewery.business.brewing.entity.KettleEvent;
import nl.ordina.brewery.business.brewing.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;

public class KitchenTimerEvent implements KettleEvent {
  private final Duration duration;
  private final Temperature temperature;

  public KitchenTimerEvent(Duration duration, Temperature temperature) {
    this.duration = duration;
    this.temperature = temperature;
  }

  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("type", "KitchenTimerEvent")
        .add("duration", duration.toString())
        .add("temperature",
            Json.createObjectBuilder()
                .add("value", temperature.getValue())
                .add("unit", temperature.getUnit().name())
                .build())
        .build();
  }

  public Duration getDuration() {
    return duration;
  }

  public Temperature getTemperature() {
    return temperature;
  }

  @Override
  public String toString() {
    return "KitchenTimerEvent{" +
        "duration=" + duration +
        ", temperature=" + temperature +
        '}';
  }
}