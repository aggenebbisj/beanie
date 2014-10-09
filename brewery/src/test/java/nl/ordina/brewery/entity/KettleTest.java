package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.*;
import nl.ordina.brewery.entity.event.TemperatureChangingEvent;
import org.junit.Test;

import javax.enterprise.event.Event;

import static nl.ordina.brewery.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class KettleTest {

  @Test
  public void initiele_temperatuur_is_nul_graden_celsius() {
    assertThat(new StandardKettle().getTemperature(), is(new Temperature(0, CELSIUS)));
  }

  @Test
  public void standaard_capaciteit_is_500_liter() {
    assertThat(new StandardKettle().getCapacity(), is(new Volume(500, LITER)));
  }

  @Test
  public void temperatuur_verhogen_leidt_tot_hogere_temperatuur() {
    Event<KettleEvent> event = mock(Event.class);

    StandardKettle sut = new StandardKettle();
    sut.setEvent(event);
    sut.changeTemperatureTo(new Temperature(10, CELSIUS));

    verify(event).fire(any(TemperatureChangingEvent.class));
  }

  @Test
  public void ingredient_toevoegen_zonder_overschrijden_maximum_capaciteit() {
    StandardKettle sut = new StandardKettle(new Volume(500, LITER));
    sut.setEvent(mock(Event.class));
    try {
      sut.addIngredient(new Water(new Volume(100, LITER)));
      sut.addIngredient(new Hop(new Volume(400, LITER)));
    } catch (IllegalArgumentException e) {
      fail("Capaciteit zou niet mogen zijn overschreden");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void teveel_ingredienten_toevoegen_geeft_exceptie() {
    Kettle sut = new StandardKettle(new Volume(500, LITER));
    sut.addIngredient(new Water(new Volume(600, LITER)));
  }
}
