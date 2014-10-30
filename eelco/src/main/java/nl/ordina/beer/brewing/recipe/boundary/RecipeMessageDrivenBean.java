package nl.ordina.beer.brewing.recipe.boundary;


import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonReader;
import nl.ordina.beer.brewing.control.Brewer;

@JMSDestinationDefinition(name = "java:app/jms/RecipeQueue", interfaceName = "javax.jms.Queue")
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:app/jms/RecipeQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class RecipeMessageDrivenBean implements MessageListener {
    
    @Inject
    private transient Logger logger;

    @Inject
    private Brewer brewer;

    @Inject
    private RecipeXmlAdapter recipeAdapter;
    
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                final String s = ((TextMessage) message).getText();
                final JsonReader reader = Json.createReader(new StringReader(s));
                brewer.addActions(recipeAdapter.unmarshal(reader.readObject()).getSteps());
            } catch (JMSException e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }
}
