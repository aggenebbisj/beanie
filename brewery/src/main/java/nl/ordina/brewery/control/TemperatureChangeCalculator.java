package nl.ordina.brewery.control;

import nl.ordina.brewery.entity.Temperature;

import java.util.Objects;

public class TemperatureChangeCalculator {
  static final int MAXIMUM_TEMPERATURE_CHANGE = 25;
  private final Temperature current;
  private final Temperature goal;

  public TemperatureChangeCalculator(Temperature current, Temperature goal) {
    Objects.requireNonNull(current);
    Objects.requireNonNull(goal);

    this.current = current;
    this.goal = goal;
  }

  private Temperature calculateChangePerTimeslot(Temperature difference) {
    final boolean maximum = difference.getValue() > MAXIMUM_TEMPERATURE_CHANGE;
    return maximum ? new Temperature(MAXIMUM_TEMPERATURE_CHANGE, difference.getUnit()) : difference;
  }

  public boolean isEqual() {
    return current.equals(goal);
  }

  public Temperature calculateNewTemperature() {
    return current.plus(calculateChangePerTimeslot(current.difference(goal)));
  }
}
