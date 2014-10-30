package nl.ordina.beer.brewing.entity;

import nl.ordina.beer.entity.Kettle;

/**
 * Interface class for all brewing actions that can be performed in this domain. E.g. adding an ingredient, changing the
 * temperature, etc. Implement this interface class when adding new actions.
 *
 * @author Ordina J-Tech
 */
public interface BrewAction {

    boolean isCompleted();

    void executeFor(Kettle kettle);
}
