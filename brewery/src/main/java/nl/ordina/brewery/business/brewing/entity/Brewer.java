package nl.ordina.brewery.business.brewing.entity;

import nl.ordina.brewery.business.brewing.entity.event.IngredientAddedEvent;
import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangedEvent;
import nl.ordina.brewery.business.brewing.entity.event.TemperatureChangingEvent;
import nl.ordina.brewery.business.brewing.entity.event.TimerExpiredEvent;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class Brewer {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());

  @Inject private Kettle kettle;

  private Iterator<Step> steps;
  private Step currentStep;
  private Iterator<Action> actions;

  @Lock(LockType.WRITE)
  public void brew(Recipe recipe) {
    steps = recipe.getSteps().iterator();
    nextStep();
  }

  public void changing(@Observes TemperatureChangingEvent event) {
    log.log(Level.INFO, "Temperature changing current {0}, goal {1} .... do nothing", new Object[] {event.getKettle().getTemperature(), event.getGoal()});
  }
  public void changed(@Observes TemperatureChangedEvent event) {
    log.log(Level.INFO, "Temperature stable at {0} execute next action", event.getGoal());
    nextAction();
  }

  public void timerExpired(@Observes TimerExpiredEvent event) {
    log.log(Level.INFO, "Timer expired after {0} .... execute next action", event.getDuration());
    nextAction();
  }
  public void changing(@Observes IngredientAddedEvent event) {
    log.log(Level.INFO, "Ingredient {0} added .... execute next action", event.getIngredient());
    nextAction();
  }


  private void nextAction() {
    if(actions.hasNext()) actions.next().executeFor(kettle);
    else                  nextStep();
  }

  private void nextStep() {
    if(steps.hasNext()) {
      currentStep = steps.next();
      log.log(Level.INFO, "New step executing {0}", currentStep);
      actions = currentStep.getActions().iterator();
      if(actions.hasNext()) actions.next().executeFor(kettle);
    }
  }
}
