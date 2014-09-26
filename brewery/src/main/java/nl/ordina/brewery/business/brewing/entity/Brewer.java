package nl.ordina.brewery.business.brewing.entity;

import javax.enterprise.event.Observes;

public class Brewer {

  private final Kettle kettle;

  public Brewer(Kettle kettle) {
    this.kettle = kettle;
  }

  public void brew(Recipe recipe) {
    // current = recipe.getSteps().get(0);
    for (Step step : recipe.getSteps()) {
      step.getIngredients().stream()
          .forEach(kettle::addIngredient);

      step.getActions().stream()
          .forEach(a -> a.executeFor(kettle));
//      step.next().executeFor(kettle);
    }
  }


  public void event(TemperatureEvent event) {

  }
}
