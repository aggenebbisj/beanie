package nl.ordina.beer.automaticbrewing.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import nl.ordina.beer.automaticbrewing.control.RecipeBrewer;

@Path("recipe")
public class RecipeResource {

    @Inject
    RecipeXmlAdapter recipeAdapter;

    @Inject
    RecipeBrewer brewer;
            
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(JsonObject recipe) {
        brewer.brew(recipeAdapter.unmarshal(recipe));
    }

}
