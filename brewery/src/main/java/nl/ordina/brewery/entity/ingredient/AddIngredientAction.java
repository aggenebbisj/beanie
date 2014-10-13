package nl.ordina.brewery.entity.ingredient;

import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

public class AddIngredientAction implements KettleAction {
  private final Ingredient ingredient;

  public AddIngredientAction(Ingredient ingredient) {
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
