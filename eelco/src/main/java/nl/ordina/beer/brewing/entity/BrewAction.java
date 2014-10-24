package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

import javax.json.JsonObject;

/**
 * Abstract class for all brewing actions that can be performed in this domain. E.g. adding an ingredient, changing the
 * temperature, etc. Extend this class when adding new actions.
 *
 * @author Ordina J-Tech
 */
public interface BrewAction {

    /**
     * Override this method in specific events to add custom behavior.
     */
     boolean isCompleted();

    /**
     * Execute the Action on the given kettle
     *
     * @param kettle
     *         -
     */
     void executeFor(Kettle kettle);

    /**
     * @return json representation of this action to send it as an event to the monitor.
     */
     JsonObject toJson();

}
