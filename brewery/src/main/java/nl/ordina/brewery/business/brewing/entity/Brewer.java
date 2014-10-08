package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.boundary.RecipeExecutor;
import nl.ordina.brewery.business.brewing.entity.event.*;

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
    nextAction();
  }

  @Asynchronous
  public void changing(@Observes KettleEvent event) {
    if (event instanceof IngredientAddedEvent || event instanceof TimerExpiredEvent || event instanceof TemperatureChangedEvent) {
      if (executor.isDone()) recipeComplete.fire(new RecipeCompleteEvent());
      else nextAction(event);
    }
    else
      log.log(INFO, "Ignoring event: {0}", event);
  }

  private void nextAction(KettleEvent event) {
    log.log(INFO, "Next action after receiving {0}", event);
    nextAction();
  }

  private void nextAction() {
    executor.nextAction(kettle);
  }

}
