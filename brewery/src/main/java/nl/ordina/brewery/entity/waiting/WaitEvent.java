package nl.ordina.brewery.entity.waiting;

import nl.ordina.brewery.entity.MonitoringEvent;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.Duration;

public class WaitEvent implements MonitoringEvent {
  private final Duration duration;

  public WaitEvent(Duration duration) {
    this.duration = duration;
  }

  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("event", "kitchen timer started")
        .add("duration", duration.toString())
        .build();
  }

  public Duration getDuration() {
    return duration;
  }

  @Override
  public String toString() {
    return "KitchenTimerEvent{" +
        "duration=" + duration +
        '}';
  }
}
