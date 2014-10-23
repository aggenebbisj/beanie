
package nl.ordina.beer.brewing.boundary;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ordina.beer.boundary.DurationXmlAdapter;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import nl.ordina.beer.brewing.control.Brewer;
import nl.ordina.brewery.entity.Manual;

@Path("brewer")
public class BrewerResource {
    
    private transient Logger log = Logger.getLogger(getClass().getName());
    
    @Inject @Manual
    private Brewer brewer;

    @Inject
    DurationXmlAdapter durationAdapter;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("ingredients")
    public void add(Ingredient ingredient) {
        log.info(() -> String.format("Manual Brewer: Adding ingredient %s", ingredient));
        brewer.addIngredient(ingredient);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("ingredients")
    public List<Ingredient> get() {
        log.info(() -> "Manual Brewer: Getting ingredients");
        return brewer.getIngredients();
    }

    @DELETE
    @Path("ingredients")
    public void delete() {
        log.info(() -> "Manual Brewer: Flushing the kettle...");
        brewer.emptyKettle();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("kettle")
    public Kettle kettleInfo() {
        log.info(() -> "Manual Brewer: Sending kettle info...");
        return brewer.getKettle();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("kettle/temperature")
    public void putTemperature(Temperature goal) {
        log.info(() -> "Manual Brewer: Changing temperature to " + goal);
        brewer.changeTemperatureTo(goal);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("kettle/temperature/stable")
    public void lock(String minutes) {
        log.info(() -> "Manual Brewer: Keeping temperature stable for " + minutes);
        brewer.lockKettle(durationAdapter.unmarshal(minutes));
    }
    
    
}
