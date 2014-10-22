
package nl.ordina.beer.control;

import javax.json.JsonObject;
import nl.ordina.beer.entity.Kettle;

public interface NotificationEvent {

    /**
     * Indicated if the event is completely done
     * E.g. if the temperature goal is reached, ingredient is added, etc.
     */
    boolean isCompleted();
    
    Kettle getKettle();
    
    JsonObject toJson();
}
