package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.AddIngredientAction;
import nl.ordina.brewery.entity.temperature.ChangeTemperatureAction;
import nl.ordina.brewery.entity.waiting.WaitAction;
import org.junit.Test;

import java.time.Duration;

import static junit.framework.TestCase.assertTrue;
import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
import nl.ordina.brewery.entity.ingredient.Ingredient;
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
        recipe.addStep(new AddIngredientAction(new Ingredient("Water", new Volume(20, LITER))));
        recipe.addStep(new ChangeTemperatureAction(Temperature.of(65, CELSIUS)));
        recipe.addStep(new AddIngredientAction(new Ingredient("Barley", new Volume(1, LITER))));
        recipe.addStep(new WaitAction(Duration.ofMinutes(30)));

        assertThat(recipe.getIngredients().size(), is(2));
    }

}
