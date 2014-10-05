package nl.ordina.brewery.business.brewing.entity.action;

import nl.ordina.brewery.business.brewing.entity.Ingredient;
import nl.ordina.brewery.business.brewing.entity.Kettle;
import nl.ordina.brewery.business.brewing.entity.KettleAction;

public class AddIngredient implements KettleAction {
  private final Ingredient ingredient;

  public AddIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  @Override
  public void executeFor(Kettle kettle) {
    kettle.addIngredient(ingredient);
  }
}
