package nl.ordina.brewery.recipe.boundary;

import nl.ordina.brewery.recipe.entity.Brewer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.logging.Logger.getLogger;
import nl.ordina.brewery.recipe.entity.Recipe;

@Path("brewer")
@ApplicationScoped
public class BrewerResource {

    private static final Logger log = getLogger(lookup().lookupClass().getName());

    @Inject
    Brewer brewer;

    @POST
    @Path("recipe")
    public void recipe(Recipe recipe) {
        log.log(Level.INFO, "Starting a new recipe with {0}", recipe);

        brewer.brew(recipe);

    }

}
