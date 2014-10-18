package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.Automatic;
import nl.ordina.brewery.entity.Kettle;

import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.logging.Level.INFO;
import static java.util.logging.Logger.getLogger;

@Singleton
public class Brewer {

    private static final Logger log = getLogger(lookup().lookupClass().getName());

    @Inject
    @Automatic
    private Kettle kettle;
    @Inject
    private Event<RecipeCompletedEvent> recipeCompleted;

    private RecipeExecutor executor;

    public void brew(Recipe recipe) {
        System.out.println("---- Brewing for kettle: " + kettle.getName());
        executor = new RecipeExecutor(recipe);
        executor.nextAction(kettle);
    }

    @Asynchronous
    public void changing(@Observes RecipeStepCompletedEvent event) {
        log.log(INFO, "Brewer received event {0}", event);

        if (executor.isDone()) {
            recipeCompleted.fire(new RecipeCompletedEvent());
        } else {
            executor.nextAction(kettle);
        }
    }

}
