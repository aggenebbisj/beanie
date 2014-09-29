package nl.ordina.brewery.business.brewing.entity.action;

import nl.ordina.brewery.business.brewing.entity.Action;
import nl.ordina.brewery.business.brewing.entity.Ingredient;
import nl.ordina.brewery.business.brewing.entity.Kettle;
import nl.ordina.brewery.business.brewing.entity.Temperature;

public class AddIngredient implements Action {
  private final Ingredient ingredient;

  public AddIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.addIngredient(ingredient);
  }
}
