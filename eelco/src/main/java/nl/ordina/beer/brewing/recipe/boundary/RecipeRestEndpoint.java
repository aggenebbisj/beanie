package nl.ordina.beer.brewing.recipe.boundary;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.json.JsonObject;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * This is the recipe REST endpoint for the path " brewer/recipe". It consumes a JSON recipe object with a htpp POST
 * method and sends it via JMS to the backend. The JSON object is send as text message. The JMS Connection factory uses the JNDI
 * java:comp/DefaultJMSConnectionFactory. The actual queue is registered at JNDI java:app/jms/RecipeQueue
 */
//TODO
public class RecipeRestEndpoint {

    private static final Logger log = getLogger(RecipeRestEndpoint.class.getName());

    //TODO
    private ConnectionFactory connectionFactory;

    //TODO
    private Queue queue;

    //TODO
    public void post(JsonObject recipe) {

    }

}
