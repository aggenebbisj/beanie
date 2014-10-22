package nl.ordina.beer.automaticbrewing.boundary;

import com.sun.messaging.jmq.jmsclient.JMSContextImpl;

import javax.jms.*;
import javax.json.Json;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.StringWriter;

import static com.sun.messaging.jmq.jmsclient.ContainerType.JavaSE;

public class JMSClient implements MessageListener {

    public static void main(String[] args) {
        // Works because of dependency to gfclient (Glassfish client)
        final ConnectionFactory connectionFactory;
        final Queue requestQueue;
        try {
            InitialContext jndiContext = new InitialContext();

            connectionFactory = (ConnectionFactory) jndiContext.lookup("java:comp/DefaultJMSConnectionFactory");

            requestQueue = (Queue) jndiContext.lookup("java:global/jms/RecipeQueue");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        JMSContext context = new JMSContextImpl(connectionFactory, JavaSE);

        final StringWriter writer = new StringWriter();
        Json.createWriter(writer).write(RecipeBuilder.aRecipeJson());
        context.createProducer().send(requestQueue, writer.getBuffer().toString());
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Message received: " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
