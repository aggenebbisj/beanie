
package nl.ordina.brewery.manual.boundary;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import nl.ordina.brewery.entity.Kettle;
import nl.ordina.brewery.entity.producer.Manual;

@Path("kettle/ingredients")
public class IngredientResource {
    
    @Inject @Manual
    Kettle kettle;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(IngredientJson ingredient) {
        System.out.println("---- Kettle: " + kettle.getName());
        kettle.addIngredient(ingredient.toDomain());
    }
    
    @GET
    public String get() {
        return "";
    }
    
    @DELETE
    public void delete() {
        
    }
}
