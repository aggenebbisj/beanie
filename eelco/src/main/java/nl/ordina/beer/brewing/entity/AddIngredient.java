package nl.ordina.beer.brewing.entity;

import java.util.Objects;
import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
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
     * Concrete implementation of javax.websocket.Encoder.Text<AddIngredient>
     * that marshals an AddIngredient object to a JSON string.
     */
    public static class Encoder implements javax.websocket.Encoder.Text<AddIngredient> {

        @Override
        public String encode(final AddIngredient ai) throws EncodeException {
            return Json.createObjectBuilder()
                    .add("event", "ingredient added").add("ingredient", Json.createObjectBuilder()
                            .add("name", ai.ingredient.getName())
                            .add("volume", Json.createObjectBuilder()
                                .add("value", ai.ingredient.getVolume().getValue())
                                .add("unit", ai.ingredient.getVolume().getUnit().name())
                                .build())
                            .build())
                    .build().toString();
        }

        @Override
        public void init(EndpointConfig config) {
        }

        @Override
        public void destroy() {
        }

    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AddIngredient other = (AddIngredient) obj;
        if (!Objects.equals(this.ingredient, other.ingredient)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddIngredient{" + "ingredient=" + ingredient + ", completed=" + completed + '}';
    }

}
