package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.Volume;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.AddIngredientAction;
import nl.ordina.brewery.entity.temperature.ChangeTemperatureAction;
import nl.ordina.brewery.entity.waiting.WaitAction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.util.Arrays;
import javax.enterprise.event.Event;
import nl.ordina.brewery.entity.Kettle;

import static nl.ordina.brewery.entity.temperature.Temperature.TemperatureUnit.CELSIUS;
import static nl.ordina.brewery.entity.Volume.VolumeUnit.LITER;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BrewerTest {

  @Mock
  private Kettle kettle;
  @Mock
  private Event<RecipeCompletedEvent> recipeCompleted;
  @InjectMocks
  private Brewer sut;

  @Test
  public void brewer_should_brew_recipe() {
    Recipe recipe = new Recipe();
    Ingredient water = new Ingredient("Water", new Volume(20, LITER));
    Ingredient barley = new Ingredient("Barley", new Volume(1, LITER));
    Temperature temperature = Temperature.of(65, CELSIUS);
    Duration duration = Duration.ofMinutes(30);

    recipe.addStep(new AddIngredientAction(water));
    recipe.addStep(new ChangeTemperatureAction(temperature));
    recipe.addStep(new AddIngredientAction(barley));
    recipe.addStep(new WaitAction(duration));

    sut.brew(recipe);

    verify(kettle).addIngredient(water);
    //TODO fix this test
    //verify(kettle).changeInternalTemperature(temperature);
  }
  
//  @Test
//  public void when_recipe_is_finished_kettle_should_be_unlocked() {
//    Recipe recipe = new Recipe();
//    recipe.addStep("DoNothing", Arrays.asList(new ChangeTemperatureAction(Temperature.of(65, CELSIUS))));
//    sut.brew(recipe);
//    sut.waitCompleted(null);
//    verify(kettle).unlock();
//  }
}
