package nl.ordina.beer.brewing.recipe.boundary;

import nl.ordina.beer.brewing.control.Brewer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@JMSDestinationDefinition(name = "java:app/jms/RecipeQueue", interfaceName = "javax.jms.Queue")
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "destinationLookup",
                                                             propertyValue = "java:app/jms/RecipeQueue"),
                                   @ActivationConfigProperty(propertyName = "destinationType",
                                                             propertyValue = "javax.jms.Queue")})
public class RecipeMessageDrivenBean implements MessageListener {

    private static final Logger log = getLogger(RecipeMessageDrivenBean.class.getName());

    @Inject
    private Brewer brewer;

    @Inject
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
