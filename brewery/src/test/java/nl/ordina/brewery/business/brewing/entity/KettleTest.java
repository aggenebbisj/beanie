package nl.ordina.brewery.business.brewing.entity;

import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangingEvent;
import org.junit.Test;

import javax.enterprise.event.Event;

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
    Event<KettleEvent> event = mock(Event.class);

    Kettle sut = new Kettle();
    sut.setEvent(event);
    sut.changeTemperatureTo(new Temperature(10, CELSIUS));

    verify(event).fire(any(TemperatureChangingEvent.class));
  }

  @Test
  public void ingredient_toevoegen_zonder_overschrijden_maximum_capaciteit() {
    Kettle sut = new Kettle(new Volume(500, LITER));
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
    Kettle sut = new Kettle(new Volume(500, LITER));
    sut.addIngredient(new Water(new Volume(600, LITER)));
  }
}
