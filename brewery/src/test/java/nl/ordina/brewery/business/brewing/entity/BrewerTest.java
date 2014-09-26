package nl.ordina.brewery.business.brewing.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.util.Arrays;

import static nl.ordina.brewery.business.brewing.entity.Temperature.Schaal.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeEenheid.LITER;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BrewerTest {

  @Mock
  private Kettle kettle;
  private Brewer sut;

  @Before
  public void setUp() throws Exception {
    sut = new Brewer(kettle);
  }

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
