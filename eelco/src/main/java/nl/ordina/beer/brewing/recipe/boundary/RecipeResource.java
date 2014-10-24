package nl.ordina.beer.brewing.recipe.boundary;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@Path("brewer/recipe")
//TODO
public class RecipeResource {

    private static final Logger log = getLogger(RecipeResource.class.getName());

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:app/jms/RecipeQueue")
    private Queue queue;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(JsonObject recipe) {
        try (JMSContext context = connectionFactory.createContext();) {
            context.createProducer().send(queue, recipe.toString());
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
