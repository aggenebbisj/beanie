package nl.ordina.brewery.entity.action;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;
import nl.ordina.brewery.entity.Temperature;

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
