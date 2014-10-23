package nl.ordina.beer.automaticbrewing.boundary;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonReader;
import java.io.StringReader;
import javax.ejb.MessageDriven;
import nl.ordina.beer.automaticbrewing.control.RecipeBrewer;
//
//@JMSDestinationDefinition(name = "java:global/jms/RecipeQueue", interfaceName = "javax.jms.Queue")
//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "destinationLookup",
//            propertyValue = "java:global/jms/RecipeQueue"),
//    @ActivationConfigProperty(propertyName = "destinationType",
//            propertyValue = "javax.jms.Queue")
//})
@MessageDriven(mappedName = "jms/RecipeQueue")
public class RecipeMDB implements MessageListener {

    @Inject
    RecipeXmlAdapter recipeAdapter;

    @Inject
    RecipeBrewer brewer;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String s = ((TextMessage) message).getText();
                final JsonReader reader = Json.createReader(new StringReader(s));
                brewer.brew(recipeAdapter.unmarshal(reader.readObject()));
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
