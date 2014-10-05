package nl.ordina.brewery.business.brewing;

import nl.ordina.brewery.business.brewing.boundary.RecipeBuilder;

import javax.jms.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import java.io.StringWriter;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;
import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;
import static nl.ordina.brewery.business.brewing.boundary.RecipeBuilder.*;

public class JMSClient implements MessageListener {
  public static void main(String[] args) {
    // Works because of dependency to gfclient (Glassfish client)
    //
    try {
      InitialContext jndiContext = new InitialContext();

      ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/ConnectionFactory");

      Queue requestQueue = (Queue) jndiContext.lookup("jms/RecipeQueue");
      Queue replyQueue = (Queue) jndiContext.lookup("jms/RecipeReplyQueue");

      Connection connection = connectionFactory.createConnection();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


      MessageConsumer consumer = session.createConsumer(replyQueue);

      consumer.setMessageListener(new JMSClient());

      connection.start();

      final MessageProducer producer = session.createProducer(requestQueue);
      final StringWriter writer = new StringWriter();
      Json.createWriter(writer).write(createRecipe());

      producer.send(session.createTextMessage(writer.getBuffer().toString()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static JsonObject createRecipe() {
    final JsonArrayBuilder actions = createArrayBuilder()
        .add(createAddIngredientAction(createIngredient("water", 10)))
        .add(RecipeBuilder.createChangeTemperature(65))
        .add(createAddIngredientAction(createIngredient("malt", 1)))
        .add(RecipeBuilder.createStableTemperature(Duration.of(30, MINUTES)));

    return createObjectBuilder()
        .add("steps", createArrayBuilder().add(createStep("Mashing", actions)))
        .build();
  }

  public void onMessage(Message message) {
    try {
      System.out.println("Message received: " + ((TextMessage) message).getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
