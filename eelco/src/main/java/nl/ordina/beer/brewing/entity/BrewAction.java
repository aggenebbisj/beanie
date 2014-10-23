
package nl.ordina.beer.brewing.entity;

import static java.lang.String.format;
import java.util.logging.Logger;
import javax.json.JsonObject;
import nl.ordina.beer.entity.Kettle;

/**
 * Abstract class for all brewing actions that can be performed in this 
 * domain. E.g. adding an ingredient, changing the temperature, etc. Extend
 * this class when adding new actions.
 * 
 * @author Remko de Jong
 */
public abstract class BrewAction {
    
    private transient Logger logger = Logger.getLogger(getClass().getName());
    
    /**
     * @param kettle The kettle on which the brewing is performed
     */
    public void brew(Kettle kettle) {
        logger.info(() -> format("Brewing %s for kettle %s", this, kettle));
        executeFor(kettle);
    }

    /**
     * Override this method in specific events to add custom behavior.
     * 
     * @param kettle The kettle can be used to check if the event is completed,
     *               e.g. if the kettle is heated up enough
     */
    public boolean isCompleted(Kettle kettle) {
        return true;
    }
    
    public abstract void executeFor(Kettle kettle);
    
    public abstract JsonObject toJson();

}
