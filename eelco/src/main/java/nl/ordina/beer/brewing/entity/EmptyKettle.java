package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

public class EmptyKettle implements BrewAction {

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public void executeFor(Kettle kettle) {
        kettle.flush();
    }

    public static class Encoder implements javax.websocket.Encoder.Text<EmptyKettle> {

        @Override
        public String encode(final EmptyKettle object) throws EncodeException {
            return Json.createObjectBuilder().add("event", "kettle emptied").build().toString();
        }

        @Override
        public void init(final EndpointConfig config) {
        }

        @Override
        public void destroy() {
        }
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
    
}
