
package nl.ordina.beer.brewing.entity;

import javax.json.JsonObject;
import nl.ordina.beer.entity.Kettle;

/**
 * Abstract class for all brewing actions that can be performed in this 
 * domain. E.g. adding an ingredient, changing the temperature, etc. Extend
 * this class when adding new actions.
 * 
 * @author Ordina J-Tech
 */
public abstract class BrewAction {

    /**
     * Override this method in specific events to add custom behavior.
     * 
     */
    public boolean isCompleted() {
        return true;
    }
    
    public abstract void executeFor(Kettle kettle);
    
    // TODO: Why json: use standard encode/decode or JAXB
    public abstract JsonObject toJson();

}
