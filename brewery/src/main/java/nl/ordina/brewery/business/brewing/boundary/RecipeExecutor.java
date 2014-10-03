package nl.ordina.brewery.business.brewing.boundary;

import nl.ordina.brewery.business.brewing.entity.Kettle;
import nl.ordina.brewery.business.brewing.entity.KettleAction;
import nl.ordina.brewery.business.brewing.entity.Recipe;
import nl.ordina.brewery.business.brewing.entity.Step;

import java.util.Iterator;

import static java.util.Collections.emptyIterator;

public class RecipeExecutor {
  private final Iterator<Step> steps;
  private Iterator<KettleAction> actions = emptyIterator();

  public RecipeExecutor(Recipe recipe) {
    steps = recipe.getSteps().iterator();
    nextStep();
  }

  public void nextAction(Kettle kettle) {
    if(actions.hasNext()) actions.next().executeFor(kettle);
    else                  nextStep();
  }

  private void nextStep() {
    if(steps.hasNext()) {
      actions = steps.next().getActions().iterator();
    }
  }

  public boolean isDone() {
    return !steps.hasNext() && !actions.hasNext();
  }

}
