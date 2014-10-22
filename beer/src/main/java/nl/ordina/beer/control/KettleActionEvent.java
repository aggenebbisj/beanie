package nl.ordina.beer.control;

import nl.ordina.beer.entity.Kettle;

/**
 *
 * @author remko
 */
public interface KettleActionEvent {

    /**
     * The kettle on which the action was performed
     */
    Kettle getKettle();
    
    /**
     * Indicated if the event is completely done
     * E.g. if the temperature goal is reached, ingredient is added, etc.
     */
    boolean isCompleted();
}
