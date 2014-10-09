package nl.ordina.brewery.entity.event;

import nl.ordina.brewery.entity.ActionableEvent;
import nl.ordina.brewery.entity.Ingredient;

import javax.json.Json;
import javax.json.JsonObject;

public class IngredientAddedEvent implements ActionableEvent {
  private final Ingredient ingredient;

  public IngredientAddedEvent(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  @Override
  public JsonObject createJson() {
    return Json.createObjectBuilder()
        .add("event", "ingredient added")
        .add("ingredient",
            Json.createObjectBuilder()
              .add("name", ingredient.getName())
              .add("volume",
                  Json.createObjectBuilder()
                      .add("value", ingredient.getVolume().getValue())
                      .add("unit", ingredient.getVolume().getUnit().name())
                      .build())
              .build())
        .build();
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  @Override
  public String toString() {
    return "IngredientAddedEvent{" +
        "ingredient=" + ingredient +
        '}';
  }
}
