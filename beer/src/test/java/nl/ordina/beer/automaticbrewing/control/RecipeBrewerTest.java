package nl.ordina.beer.automaticbrewing.control;

import javax.enterprise.event.Event;
import nl.ordina.beer.automaticbrewing.boundary.RecipeBuilder;
import nl.ordina.beer.automaticbrewing.entity.Recipe;
import nl.ordina.beer.control.IngredientAddedEvent;
import nl.ordina.beer.control.TemperatureChangedEvent;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import static nl.ordina.beer.entity.Temperature.TemperatureUnit.CELSIUS;
import nl.ordina.beer.brewing.control.Brewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RecipeBrewerTest {

    @InjectMocks
    private RecipeBrewer sut;

    @Mock
    private Brewer brewer;

    @Mock
    private Event<RecipeCompletedEvent> event;

    private Kettle kettle;

    @Before
    public void setup() {
        sut.brewer = brewer;
        kettle = new Kettle("UberKettle 2000");
    }

    @Test
    public void should_begin_with_first_step() {
        sut.brew(RecipeBuilder.aRecipe());
        Mockito.verify(brewer).addIngredient(RecipeBuilder.defaultIngredient());
    }
    
    @Test
    public void should_trigger_next_step_on_event() {
        Recipe recipe = RecipeBuilder.aRecipe();
        sut.setRecipe(recipe);
        recipe.nextStep();
        sut.onStepCompleted(new IngredientAddedEvent(RecipeBuilder.defaultIngredient(), kettle));
        Mockito.verify(brewer).changeTemperatureTo(RecipeBuilder.defaultTemperature());
    }

    @Test
    public void should_not_trigger_next_step_on_event_from_manual_kettle() {
        kettle.setName("Ye Olde Kettle");
        sut.setRecipe(RecipeBuilder.aRecipe());
        sut.onStepCompleted(new IngredientAddedEvent(RecipeBuilder.defaultIngredient(), kettle));
        Mockito.verifyZeroInteractions(brewer);
    }

    @Test
    public void should_not_trigger_next_step_on_incompleted_event() {
        kettle.setTemperature(new Temperature(50, CELSIUS));
        sut.setRecipe(RecipeBuilder.aRecipe());
        sut.onStepCompleted(new TemperatureChangedEvent(new Temperature(30, CELSIUS), kettle));
        Mockito.verifyZeroInteractions(brewer);
    }

    @Test
    public void should_fire_recipe_completed_event_if_no_more_steps() {
        sut.setRecipe(RecipeBuilder.anEmptyRecipe());
        sut.onStepCompleted(new IngredientAddedEvent(RecipeBuilder.defaultIngredient(), kettle));
        Mockito.verify(event).fire(new RecipeCompletedEvent(RecipeBuilder.anEmptyRecipe().getName()));
    }

}
