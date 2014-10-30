
package nl.ordina.beer.brewing.boundary;

import static java.lang.String.format;
import java.time.Duration;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ordina.beer.brewing.entity.AddIngredient;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.brewing.control.Brewer;
import nl.ordina.beer.brewing.entity.ChangeTemperature;
import nl.ordina.beer.brewing.entity.EmptyKettle;
import nl.ordina.beer.brewing.entity.KeepTemperatureStable;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;

/**
 * RESTful endpoint for manual brewing.
 * 
 * @author Ordina J-Tech
 */
@Path("brewer")
public class BrewerResource {
    
    @Inject
    private transient Logger logger;
    
    @Inject 
    private Brewer brewer;

    @Inject
    DurationXmlAdapter durationAdapter;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("ingredients")
    public void addIngredient(Ingredient ingredient) {
        logger.finest(() -> format("Manual: Adding ingredient %s", ingredient));
        brewer.addAction(new AddIngredient(ingredient));
    }

    @DELETE
    @Path("ingredients")
    public void emptyKettle() {
        logger.finest(() -> "Manual: Emptying the kettle...");
        brewer.addAction(new EmptyKettle());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("kettle")
    public Kettle getKettle() {
        logger.finest(() -> format("Manual: Getting kettle %s", brewer.getKettle()));
        return brewer.getKettle();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("kettle/temperature")
    public void changeTemperatureTo(Temperature goal) {
        logger.finest(() -> "Manual: Changing temperature to " + goal);
        brewer.addAction(new ChangeTemperature(goal));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("kettle/temperature/stable")
    public void keepTemperatureStable(String minutes) {
        Duration duration = durationAdapter.unmarshal(minutes);
        logger.finest(() -> "Manual: Keeping temperature stable for " + minutes);
        brewer.addAction(new KeepTemperatureStable(duration));
    }
    
}