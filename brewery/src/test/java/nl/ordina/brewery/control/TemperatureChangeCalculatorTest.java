package nl.ordina.brewery.control;

import nl.ordina.brewery.entity.temperature.Temperature;
import org.junit.Test;

import static nl.ordina.brewery.control.TemperatureChangeCalculator.MAXIMUM_TEMPERATURE_CHANGE;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.temperature.Temperature.of;
import static org.junit.Assert.*;

public class TemperatureChangeCalculatorTest {

  public static final Temperature FOURTEEN = new Temperature(14, CELSIUS);
  public static final Temperature TEN = new Temperature(10, CELSIUS);

  @Test
  public void equalTemperatures() {

    TemperatureChangeCalculator sut = create(TEN, TEN);

    assertTrue("Temperature should be equal", sut.isEqual());
  }

  @Test
  public void goalHigherThenCurrent() {
    TemperatureChangeCalculator sut = create(TEN, FOURTEEN);

    assertFalse(sut.isEqual());
    assertEquals(FOURTEEN, sut.calculateNewTemperature());
  }


  @Test
  public void currentHigherThenGoal() {
    TemperatureChangeCalculator sut = new TemperatureChangeCalculator(FOURTEEN, TEN);

    assertEquals(TEN, sut.calculateNewTemperature());
  }

  @Test
  public void deltaMoreThenMaximum() {
    TemperatureChangeCalculator sut = new TemperatureChangeCalculator(TEN, new Temperature(100, CELSIUS));

    assertEquals(of(10 + MAXIMUM_TEMPERATURE_CHANGE, CELSIUS), sut.calculateNewTemperature());
  }

  private TemperatureChangeCalculator create(Temperature current, Temperature goal) {
    return new TemperatureChangeCalculator(current, goal
    );
  }


}
