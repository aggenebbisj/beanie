
package nl.ordina.beer.manualbrewing.boundary;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ordina.beer.entity.Ingredient;
import nl.ordina.beer.entity.Kettle;
import nl.ordina.beer.entity.Temperature;
import nl.ordina.beer.manualbrewing.control.Brewer;

@Path("brewer")
public class BrewerResource {
    
    private static final Logger log = Logger.getLogger(BrewerResource.class.getName());
    
    @EJB
    private Brewer brewer;

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
}
