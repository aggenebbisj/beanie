
package nl.ordina.brewery.manual.boundary;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import nl.ordina.brewery.entity.Kettle;

@Path("kettle/ingredients")
public class IngredientResource {
    
    @Inject
    Kettle kettle;
    
    @POST
    public void add() {
    }
    
    @GET
    public String get() {
        return "";
    }
    
    @DELETE
    public void delete() {
        
    }
}
