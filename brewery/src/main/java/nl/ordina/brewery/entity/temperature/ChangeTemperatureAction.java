package nl.ordina.brewery.entity.temperature;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

public class ChangeTemperatureAction implements KettleAction {
  private final Temperature temperature;

  public ChangeTemperatureAction(Temperature temperature) {
    this.temperature = temperature;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.changeTemperatureTo(temperature);
  }
}
