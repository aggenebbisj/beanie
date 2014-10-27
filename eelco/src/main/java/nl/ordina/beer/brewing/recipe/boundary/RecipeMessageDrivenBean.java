package nl.ordina.beer.brewing.recipe.boundary;

import nl.ordina.beer.brewing.control.Brewer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * This endpoint listens to the RecipeQueue
 */
//TODO
public class RecipeMessageDrivenBean implements MessageListener {

    private static final Logger log = getLogger(RecipeMessageDrivenBean.class.getName());

    //TODO
    private Brewer brewer;

    //TODO
    private RecipeJSONAdapter recipeAdapter;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                final String s = ((TextMessage) message).getText();
                final JsonReader reader = Json.createReader(new StringReader(s));
                brewer.addActions(recipeAdapter.unmarshal(reader.readObject()).getSteps());
            } catch (JMSException e) {
                log.log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }
}
