package nl.ordina.brewery.entity.ingredient;

import nl.ordina.brewery.entity.ingredient.Ingredient;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.KettleAction;

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
