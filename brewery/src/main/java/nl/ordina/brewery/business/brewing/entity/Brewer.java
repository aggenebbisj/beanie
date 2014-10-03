package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.boundary.RecipeExecutor;
import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangingEvent;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.logging.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.logging.Level.INFO;
import static java.util.logging.Logger.getLogger;

@Singleton
public class Brewer {
  private static final Logger log = getLogger(lookup().lookupClass().getName());

  @Inject private Kettle kettle;

  private RecipeExecutor executor;

  public void brew(Recipe recipe) {
    executor = new RecipeExecutor(recipe);
    nextAction();
  }

  public void changing(@Observes KettleEvent event) {
    if(event instanceof TemperatureChangingEvent)
      log.log(INFO, "Temperature changing current: {0}", event);
    else
      nextAction(event);
  }

  private void nextAction(KettleEvent event) {
    log.log(INFO, "Event {0}", event);
    nextAction();
  }

  private void nextAction() {
    executor.nextAction(kettle);
  }

}
