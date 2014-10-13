package nl.ordina.brewery.entity;

import java.time.Duration;
import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Hop;
import nl.ordina.brewery.entity.ingredient.Water;
import nl.ordina.brewery.entity.temperature.TemperatureChangingEvent;
import org.junit.Test;

import javax.enterprise.event.Event;

import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.capacity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KettleTest {

  @Test
  public void initiele_temperatuur_is_nul_graden_celsius() {
    assertThat(new Kettle().getTemperature(), is(new Temperature(0, CELSIUS)));
  }

  @Test
  public void standaard_capaciteit_is_500_liter() {
    assertThat(new Kettle().getCapacity(), is(new Volume(500, LITER)));
  }

  @Test
  public void temperatuur_verhogen_leidt_tot_hogere_temperatuur() {
    Event event = mock(Event.class);

    Kettle sut = new Kettle();
    sut.setEvent(event);
    sut.changeTemperatureTo(new Temperature(10, CELSIUS));

    verify(event).fire(any(TemperatureChangingEvent.class));
  }

  @Test
  public void ingredient_toevoegen_zonder_overschrijden_maximum_capaciteit() {
    final Event event = mock(Event.class);
    Kettle sut = new Kettle(new Volume(500, LITER));
    sut.setEvent(event);
    try {
      sut.addIngredient(new Water(new Volume(100, LITER)));
      sut.addIngredient(new Hop(new Volume(400, LITER)));
    } catch (IllegalArgumentException e) {
      fail("Capaciteit zou niet mogen zijn overschreden");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void teveel_ingredienten_toevoegen_geeft_exceptie() {
    Kettle sut = new Kettle(new Volume(500, LITER));
    sut.addIngredient(new Water(new Volume(600, LITER)));
  }
  
  @Ignore
  @Test(expected = IllegalStateException.class)
  public void performing_action_on_kettle_while_kettle_is_locked_should_throw_exception() {
      Kettle sut = new Kettle(new Volume(500, LITER));
      sut.lock(Duration.ZERO);
      sut.addIngredient(new Water(new Volume(300, LITER)));
  }
  
}
