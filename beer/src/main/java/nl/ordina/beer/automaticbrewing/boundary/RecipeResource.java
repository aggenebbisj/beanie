package nl.ordina.beer.automaticbrewing.boundary;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import nl.ordina.beer.manualbrewing.control.Brewer;
import nl.ordina.brewery.entity.Automatic;

@Path("recipe")
public class RecipeResource {

    @Inject @Automatic
    private Brewer brewer;

    @Inject
    RecipeXmlAdapter recipeAdapter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(JsonObject recipe) {
        recipeAdapter.unmarshal(recipe).brew(brewer);
    }

}
