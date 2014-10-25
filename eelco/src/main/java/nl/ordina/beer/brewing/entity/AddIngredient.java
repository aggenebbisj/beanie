package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;

public class AddIngredient implements BrewAction {

    private final Ingredient ingredient;

    private boolean completed;

    public AddIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void executeFor(Kettle kettle) {
        completed = false;
        kettle.addIngredient(ingredient);
        completed = true;
    }

    /**
     * Concrete implementation of javax.websocket.Encoder.Text<AddIngredient> that marshals an AddIngredient object to a
     * JSON string.
     */
    //TODO
    public static class Encoder {

    }

}
