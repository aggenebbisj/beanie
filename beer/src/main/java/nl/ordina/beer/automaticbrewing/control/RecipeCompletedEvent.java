package nl.ordina.beer.automaticbrewing.control;

import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import nl.ordina.beer.control.NotificationEvent;

public class RecipeCompletedEvent implements NotificationEvent {
    private final String name;

    public RecipeCompletedEvent(String recipeName) {
        this.name = recipeName;
    }
    
    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("event", "recipe completed")
                .add("name", name)
                .build();
    }

    @Override
    public String toString() {
        return "RecipeCompletedEvent{" + "name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final RecipeCompletedEvent other = (RecipeCompletedEvent) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
