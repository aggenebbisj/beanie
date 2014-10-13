package nl.ordina.brewery.recipe.boundary;

import nl.ordina.brewery.recipe.entity.Brewer;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.json.Json;
import javax.json.JsonReader;
import java.io.StringReader;

@MessageDriven(mappedName = "jms/RecipeQueue")
public class RecipeMessageDrivenBean implements MessageListener {
  @Inject
  Brewer brewer;
  @Inject
  RecipeParser parser;

  @Override
  public void onMessage(Message message) {
    // TODO ugly as hell, fix it
    if (message instanceof TextMessage) {
      try {
        String s = ((TextMessage) message).getText();
        final JsonReader reader = Json.createReader(new StringReader(s));
        brewer.brew(parser.parseRecipe(reader.readObject()));
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }

  }
}
