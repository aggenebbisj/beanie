package nl.ordina.beer.brewing.boundary;

import nl.ordina.beer.brewing.control.Brewer;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * RESTful endpoint for manual brewing on the <code>brewer</code> path. Please have a look at <a
 * href="http://docs.oracle.com/javaee/7/tutorial/doc/jaxrs.htm#GIEPU">Java EE 7 - JAX-RS tutorial</a> if you are new to
 * JAX-RS.
 *
 * @author Ordina J-Tech
 */
// TODO
public class BrewerResource {

    //TODO
    DurationJsonAdapter durationAdapter;

    private Logger logger = Logger.getLogger(getClass().getName());

    //TODO
    private Brewer brewer;

    /**
     * POST method that consumes JSON on the "ingredients" path. Adds a new AddIngredient BrewerAction to the brewer.
     *
     * @param ingredient
     *         Marshaled JSON object
     */
    // TODO
    public void addIngredient(Ingredient ingredient) {
    }

    /**
     * DELETE method on the "ingredients" path. Adds a new EmptyKettle BrewerAction to the brewer.
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
     * PUT method that consumes JSON on the "kettle/temperature" path. Adds a new ChangeTemperature BrewerAction to the
     * brewer.
     *
     * @param goal
     *         new temperature to be changed
     */
    // TODO
    public void changeTemperatureTo(Temperature goal) {
    }

    /**
     * PUT method that consumes JSON on the "kettle/temperature/stable" path. Adds a new KeepTemperatureStable
     * BrewerAction to the brewer.
     *
     * @param minutes
     *         duration to wait
     */
    // TODO
    public void keepTemperatureStable(String minutes) {
        Duration duration = durationAdapter.unmarshal(minutes);
    }
}
