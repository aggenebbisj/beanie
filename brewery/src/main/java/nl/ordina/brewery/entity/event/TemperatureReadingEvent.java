package nl.ordina.brewery.entity.event;

import nl.ordina.brewery.entity.MonitorableEvent;
import nl.ordina.brewery.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;

public class TemperatureReadingEvent implements MonitorableEvent {
  private final Temperature temperature;

  public TemperatureReadingEvent(Temperature temperature) {
    this.temperature = temperature;
  }

  @Override
  public JsonObject createJson() {
    return
        Json.createObjectBuilder()
            .add("event", "temperature reading")
            .add("temperature",
                Json.createObjectBuilder()
                    .add("scale", temperature.getUnit().name())
                    .add("value", temperature.getValue())
                    .build())
            .build();
  }

  @Override
  public String toString() {
    return "TemperatureChangingEvent{" +
        "current=" + temperature +
        '}';
  }
}
