package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

import java.time.Duration;

public class StableTemperature implements KettleAction {
  private final Duration duration;

  public StableTemperature(Duration duration) {
    this.duration = duration;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.keepStableTemperature(duration);
  }
}
