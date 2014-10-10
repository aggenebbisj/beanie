package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;
import nl.ordina.brewery.entity.temperature.Temperature;

public class ChangeTemperature implements KettleAction {
  private final Temperature temperature;

  public ChangeTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.changeTemperatureTo(temperature);
  }
}
