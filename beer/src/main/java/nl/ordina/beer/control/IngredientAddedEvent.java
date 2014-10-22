package nl.ordina.beer.control;

import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;

public class IngredientAddedEvent implements NotificationEvent, KettleActionEvent {

    private final Ingredient ingredient;
    private final Kettle kettle;

    public IngredientAddedEvent(Ingredient ingredient, Kettle kettle) {
        this.ingredient = ingredient;
        this.kettle = kettle;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
    
    @Override
    public Kettle getKettle() {
        return kettle;
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
                .add("kettle", kettle.getName())
                .build();
    }

    @Override
    public String toString() {
        return "IngredientAddedEvent{" + "ingredient=" + ingredient + ", kettle=" + kettle + '}';
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
        final IngredientAddedEvent other = (IngredientAddedEvent) obj;
        if (!Objects.equals(this.ingredient, other.ingredient)) {
            return false;
        }
        if (!Objects.equals(this.kettle, other.kettle)) {
            return false;
        }
        return true;
    }

}
