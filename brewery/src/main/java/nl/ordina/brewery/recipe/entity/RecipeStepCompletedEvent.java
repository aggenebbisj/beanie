package nl.ordina.brewery.recipe.entity;

import static javax.json.Json.createObjectBuilder;
import javax.json.JsonObject;
import nl.ordina.brewery.entity.MonitoredEvent;

public class RecipeStepCompletedEvent implements MonitoredEvent {

    @Override
    public JsonObject createJson() {
        return createObjectBuilder()
                .add("event", "recipe step completed")
                .build();
    }

}
