package nl.ordina.brewery.business.brewing.entity.event;

import nl.ordina.brewery.business.brewing.entity.KettleEvent;
import nl.ordina.brewery.business.brewing.entity.StandardKettle;
import nl.ordina.brewery.business.brewing.entity.Temperature;

import javax.json.Json;
import javax.json.JsonObject;

public class TemperatureReadingEvent implements KettleEvent {
  private final Temperature temperature;

  public TemperatureReadingEvent(Temperature temperature) {
    this.temperature = temperature;
  }

  @Override
  public JsonObject createJson() {
    return
        Json.createObjectBuilder()
            .add("type", "TemperatureReadingEvent")
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
