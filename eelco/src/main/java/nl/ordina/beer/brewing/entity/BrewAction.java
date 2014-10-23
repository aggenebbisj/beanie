
package nl.ordina.beer.brewing.entity;

import static java.lang.String.format;
import java.util.logging.Logger;
import javax.enterprise.event.Event;
import javax.inject.Inject;
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

    public abstract void executeFor(Kettle kettle);
    
}
