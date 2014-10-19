
package nl.ordina.brewery.manual.boundary;

import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.Manual;
import nl.ordina.brewery.entity.ingredient.Ingredients;

@Path("kettle/ingredients")
public class IngredientResource {
    
    private static final Logger log = Logger.getLogger("IngredientResource");
    
    @Inject @Manual
    Kettle kettle;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(IngredientJson ingredient) {
        log.info(() -> String.format("Kettle %s: adding ingredient %s", kettle.getName(), ingredient));
        kettle.addIngredient(ingredient.toDomain());
    }
    
    @DELETE
    public void delete() {
        log.info(() -> String.format("Kettle %s: flushing...", kettle.getName()));
        kettle.flush();
    }
}
