package nl.ordina.beer.brewing.boundary;

import java.time.Duration;
import java.util.logging.Logger;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.brewing.control.Brewer;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;

/**
 * RESTful endpoint for manual brewing on the <code>brewer</code> path.
 *
 * @author Ordina J-Tech
 */
// TODO
public class BrewerResource {

    private transient Logger logger = Logger.getLogger(getClass().getName());

    private Brewer brewer;

    DurationXmlAdapter durationAdapter;

    /**
     * POST method that consumes JSON on the "ingredients" path. Adds a new
     * AddIngredient BrewerAction to the brewer.
     *
     * @param ingredient Marsalled JSON object
     */
    // TODO
    public void addIngredient(Ingredient ingredient) {
    }

    /**
     * DELETE method on the "ingredients" path. Adds a new EmptyKettle
     * BrewerAction to the brewer.
     */
    // TODO
    public void emptyKettle() {
    }

    /**
     * GET method on the "kettle" path
     *
     * @return the Kettle object from the brewer
     */
    // TODO
    public Kettle getKettle() {
        return null;
    }

    /**
     * PUT method that consumes JSON on the "kettle/temperature" path. Adds a
     * new ChangeTemperature BrewerAction to the brewer.
     *
     * @param goal new temperature to be changed
     */
    // TODO
    public void changeTemperatureTo(Temperature goal) {
    }

    /**
     * PUT method that consumes JSON on the "kettle/temperature/stable" path.
     * Adds a new KeepTemperatureStable BrewerAction to the brewer.
     *
     * @param minutes duration to wait
     */
    // TODO
    public void keepTemperatureStable(String minutes) {
        Duration duration = durationAdapter.unmarshal(minutes);
    }
}
