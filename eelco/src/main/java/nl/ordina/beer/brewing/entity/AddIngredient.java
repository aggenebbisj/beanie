package nl.ordina.beer.brewing.entity;

import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;

public class AddIngredient extends BrewAction {

    private final Ingredient ingredient;

    public AddIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public void executeFor(Kettle kettle) {
        kettle.addIngredient(ingredient);
    }

    @Override
    public JsonObject toJson() {
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

    //TODO: Why?
    @Override
    public String toString() {
        return "AddIngredient{" + "ingredient=" + ingredient + '}';
    }

    //TODO: Why?
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    //TODO: Why?
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

}
