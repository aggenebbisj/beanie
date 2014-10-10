package nl.ordina.brewery.control;

import nl.ordina.brewery.entity.Temperature;
import nl.ordina.brewery.entity.event.TemperatureChangingEvent;
import nl.ordina.brewery.entity.event.TemperatureReachedEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import nl.ordina.brewery.entity.Kettle;

import static nl.ordina.brewery.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.Temperature.of;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ThermoregulationTest {

  public static final Temperature DEGREES_30 = of(30, CELSIUS);
  @Mock
  Event<TemperatureReachedEvent> changedEvent;
  @Mock TimerService timerService;

  @Mock
  Kettle kettle;

  @InjectMocks
  private Thermoregulation sut;

  @Test
  public void currentTemperatureDiffersFromGoal() {
    when(kettle.getTemperature()).thenReturn(DEGREES_30);

    sut.changeTemperature(createEvent(of(40, CELSIUS)));

    verify(timerService).createSingleActionTimer(anyLong(), anyObject());
  }

  @Test
  public void currentTemperatureIsGoal() {
    when(kettle.getTemperature()).thenReturn(DEGREES_30);

    sut.changeTemperature(createEvent(DEGREES_30));

    verify(changedEvent).fire(anyObject());
  }

  private TemperatureChangingEvent createEvent(Temperature temperature) {
    return new TemperatureChangingEvent(kettle, temperature);
  }
}
