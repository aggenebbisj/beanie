package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleEvent;
import nl.ordina.brewery.entity.event.*;
import nl.ordina.brewery.recipe.entity.Recipe;
import nl.ordina.brewery.recipe.entity.RecipeExecutor;

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
  private Kettle kettle;
  @Inject
  private Event<RecipeCompleteEvent> recipeComplete;

  private RecipeExecutor executor;

  public void brew(Recipe recipe) {
    executor = new RecipeExecutor(recipe);
    executor.nextAction(kettle);
  }

  @Asynchronous
  public void changing(@Observes KettleEvent event) {
    if (event instanceof IngredientAddedEvent || event instanceof TimerExpiredEvent || event instanceof TemperatureChangedEvent) {
      if (executor.isDone())
        recipeComplete.fire(new RecipeCompleteEvent());
      else
        executor.nextAction(kettle);
    }
    else
      log.log(INFO, "Ignoring event: {0}", event);
  }

}
