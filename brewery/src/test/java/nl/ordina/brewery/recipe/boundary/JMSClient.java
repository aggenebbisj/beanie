package nl.ordina.brewery.recipe.boundary;

import com.sun.messaging.jmq.jmsclient.JMSContextImpl;
import nl.ordina.brewery.entity.RecipeBuilder;

import javax.jms.*;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.StringWriter;
import java.time.Duration;

import static com.sun.messaging.jmq.jmsclient.ContainerType.JavaSE;
import static java.time.temporal.ChronoUnit.MINUTES;
import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;
import static nl.ordina.brewery.entity.RecipeBuilder.*;

public class JMSClient implements MessageListener {
  public static void main(String[] args) {
    // Works because of dependency to gfclient (Glassfish client)
    //
    final ConnectionFactory connectionFactory;
    final Queue requestQueue;
    try {
      InitialContext jndiContext = new InitialContext();

      connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/ConnectionFactory");

      requestQueue = (Queue) jndiContext.lookup("jms/RecipeQueue");
      Queue replyQueue = (Queue) jndiContext.lookup("jms/RecipeReplyQueue");
    } catch (NamingException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    JMSContext context = new JMSContextImpl(connectionFactory, JavaSE);

      final StringWriter writer = new StringWriter();
      Json.createWriter(writer).write(createRecipe());
      context.createProducer().send(requestQueue, writer.getBuffer().toString());
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