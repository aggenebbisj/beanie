
package nl.ordina.beer.brewing.recipe.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import nl.ordina.beer.brewing.control.Brewer;

@Path("brewer/recipe")
public class RecipeResource {

    @Inject
    RecipeXmlAdapter recipeAdapter;

    @Inject
    Brewer brewer;
            
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(JsonObject recipe) {
        brewer.addActions(recipeAdapter.unmarshal(recipe).getSteps());
    }

}