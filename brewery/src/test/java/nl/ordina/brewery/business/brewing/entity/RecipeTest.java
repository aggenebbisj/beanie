package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.entity.action.ChangeTemperature;
import nl.ordina.brewery.business.brewing.entity.action.StableTemperature;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static nl.ordina.brewery.business.brewing.entity.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.business.brewing.entity.Volume.VolumeUnit.LITER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RecipeTest {
  @Test
  public void newRecipe() {
    Recipe recipe = new Recipe();
    assertTrue(recipe.getIngredients().isEmpty());
  }

  @Test
  public void oneStep() {
    Recipe recipe = new Recipe();
    recipe.addStep("Mashing",
        Arrays.asList(new Water(new Volume(20, LITER)), new Malt(new Volume(1, LITER))),
        Arrays.asList(
            new ChangeTemperature(Temperature.ofDegrees(65, CELSIUS)),
            new StableTemperature(Duration.ofMinutes(30))
        ));
    assertThat(recipe.getIngredients().size(), is(2));
  }

}

