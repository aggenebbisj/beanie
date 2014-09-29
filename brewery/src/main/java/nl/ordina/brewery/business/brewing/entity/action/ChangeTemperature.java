package nl.ordina.brewery.business.brewing.entity.action;

import nl.ordina.brewery.business.brewing.entity.Action;
import nl.ordina.brewery.business.brewing.entity.Kettle;
import nl.ordina.brewery.business.brewing.entity.Temperature;

public class ChangeTemperature implements Action {
  private final Temperature temperature;

  public ChangeTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.changeTemperatureTo(temperature);
  }
}
