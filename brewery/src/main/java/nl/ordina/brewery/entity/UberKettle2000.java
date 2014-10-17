
package nl.ordina.brewery.entity;

import java.lang.invoke.MethodHandles;
import static java.util.logging.Level.INFO;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.ingredient.IngredientAddedEvent;
import nl.ordina.brewery.entity.producer.Automatic;
import nl.ordina.brewery.entity.temperature.Temperature;
import nl.ordina.brewery.entity.temperature.TemperatureReachedEvent;
import nl.ordina.brewery.entity.waiting.WaitCompletedEvent;
import nl.ordina.brewery.recipe.entity.RecipeStepCompletedEvent;

@Automatic
public class UberKettle2000 extends YeOldeKettle {

    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
    
    @Inject
    private Event<RecipeStepCompletedEvent> recipeEvent;
    
    @Inject
    private Event<MonitoredEvent> event;

    public UberKettle2000() {
        super("Uber Kettle 2000 (TM)");
    }
    
    @Override
    public void fireTemperatureReachedEvent(Temperature goal) {
        event.fire(new TemperatureReachedEvent(goal));
        recipeEvent.fire(new RecipeStepCompletedEvent());
        log.log(INFO, "Fired events: {0}, {1}", new Object[] { event, recipeEvent });
    }

    @Override
    public void fireIngredientAddedEvent(Ingredient ingredient) {
        event.fire(new IngredientAddedEvent(ingredient));
        recipeEvent.fire(new RecipeStepCompletedEvent());
        log.log(INFO, "Fired events: {0}, {1}", new Object[] { event, recipeEvent });
    }

    @Override
    public void fireWaitCompletedEvent() {
        event.fire(new WaitCompletedEvent());
        recipeEvent.fire(new RecipeStepCompletedEvent());
        log.log(INFO, "Fired events: {0}, {1}", new Object[] { event, recipeEvent });
    }

    
}
