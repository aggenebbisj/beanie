package nl.ordina.brewery.entity;

import nl.ordina.brewery.entity.capacity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Malt;
import nl.ordina.brewery.entity.ingredient.Water;
import nl.ordina.brewery.entity.ingredient.AddIngredient;
import nl.ordina.brewery.entity.temperature.ChangeTemperature;
import nl.ordina.brewery.entity.temperature.StableTemperature;
import nl.ordina.brewery.recipe.entity.Recipe;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.capacity.Volume.VolumeUnit.LITER;
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
        Arrays.asList(
            new AddIngredient(new Water(new Volume(20, LITER))),
            new ChangeTemperature(Temperature.of(65, CELSIUS)),
            new AddIngredient(new Malt(new Volume(1, LITER))),
            new StableTemperature(Duration.ofMinutes(30))

        ));
    assertThat(recipe.getIngredients().size(), is(2));
  }

}

