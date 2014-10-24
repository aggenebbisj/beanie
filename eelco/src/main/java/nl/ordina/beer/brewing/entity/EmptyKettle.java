package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

import javax.json.Json;
import javax.json.JsonObject;

public class EmptyKettle extends BrewAction {

    @Override
    public void executeFor(Kettle kettle) {
        kettle.flush();
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder().add("event", "kettle emptied").build();
    }

}
