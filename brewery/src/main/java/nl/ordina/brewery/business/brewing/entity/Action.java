package nl.ordina.brewery.business.brewing.entity;

import java.time.Duration;

public interface Action {

  void executeFor(Kettle kettle);
}

class StableTemperature implements Action {
  private final Duration duration;

  public StableTemperature(Duration duration) {
    this.duration = duration;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.keepStableTemperature(duration);
  }
}

class ChangeTemperature implements Action {
  private final Temperature temperature;

  public ChangeTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.changeTemperatureTo(temperature);
  }
}

