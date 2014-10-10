package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.MonitorableEvent;
import nl.ordina.brewery.entity.temperature.Temperature;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;

public class KitchenTimerEvent implements MonitorableEvent {
  private final Duration duration;
  private final Temperature temperature;

  public KitchenTimerEvent(Duration duration, Temperature temperature) {
    this.duration = duration;
    this.temperature = temperature;
  }

  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("event", "kitchen timer started")
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
