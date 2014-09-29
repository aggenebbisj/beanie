package nl.ordina.brewery.business.brewing.entity.action;

import nl.ordina.brewery.business.brewing.entity.Action;
import nl.ordina.brewery.business.brewing.entity.Kettle;

import java.time.Duration;

public class StableTemperature implements Action {
  private final Duration duration;

  public StableTemperature(Duration duration) {
    this.duration = duration;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.keepStableTemperature(duration);
  }
}
