
package nl.ordina.brewery.manual.boundary;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("kettle/ingredients")
public class IngredientResource {

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
