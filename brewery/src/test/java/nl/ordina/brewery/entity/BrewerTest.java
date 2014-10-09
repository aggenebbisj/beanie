package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.action.AddIngredient;
import nl.ordina.brewery.entity.action.ChangeTemperature;
import nl.ordina.brewery.entity.action.StableTemperature;
import nl.ordina.brewery.recipe.entity.Brewer;
import nl.ordina.brewery.recipe.entity.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.util.Arrays;

import static nl.ordina.brewery.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
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
    Temperature temperature = Temperature.of(65, CELSIUS);
    Duration duration = Duration.ofMinutes(30);

    recipe.addStep("Mashing",
        Arrays.asList(
            new AddIngredient(water),
            new ChangeTemperature(temperature),
            new AddIngredient(malt),
            new StableTemperature(duration)
        ));

    sut.brew(recipe);

    verify(kettle).addIngredient(water);
  }
}
