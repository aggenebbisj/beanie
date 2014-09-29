package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.entity.action.ChangeTemperature;
import nl.ordina.brewery.business.brewing.entity.action.StableTemperature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.util.Arrays;

import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BrewerTest {

  @Mock
  private Kettle kettle;
  @InjectMocks
  private Brewer sut;

  @Test
  public void test() {
    Recipe recipe = new Recipe();
    Water water = new Water(new Volume(20, LITER));
    Malt malt = new Malt(new Volume(1, LITER));
    Temperature temperature = Temperature.ofDegrees(65, CELSIUS);
    Duration duration = Duration.ofMinutes(30);

    recipe.addStep("Mashing",
        Arrays.asList(water, malt),
        Arrays.asList(
            new ChangeTemperature(temperature),
            new StableTemperature(duration)
        ));

    sut.brew(recipe);

    verify(kettle).addIngredient(water);
    verify(kettle).addIngredient(malt);
    verify(kettle).changeTemperatureTo(temperature);
//    verify(kettle, never()).keepStableTemperature(duration);
    verify(kettle).keepStableTemperature(duration);
  }
}
