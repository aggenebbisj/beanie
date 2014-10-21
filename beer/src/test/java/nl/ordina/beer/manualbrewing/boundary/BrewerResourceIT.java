package nl.ordina.beer.manualbrewing.boundary;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import javax.ws.rs.core.UriBuilder;
import nl.ordina.beer.boundary.RestApplication;
import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * @author Arun Gupta
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BrewerResourceIT {

    private static WebTarget target;
    
    /**
     * Arquillian specific method for creating a file which can be deployed
     * while executing the test.
     *
     * @return a war file
     */
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class).
                addClass(RestApplication.class).
                addClass(BrewerResource.class);
        System.out.println(war.toString(true));
        
        return war;
    }
    
    @ArquillianResource
    private URL base;

    @Before
    public void setupClass() throws MalformedURLException {
        Client client = ClientBuilder.newClient();
        target = client.target(URI.create(new URL(base, "resources/brewer/ingredients").toExternalForm()));
    }
    
    URI buildUri(String... paths) {
        UriBuilder builder = UriBuilder.fromUri(base.toString());
        for (String path : paths) {
            builder.path(path);
        }
        return builder.build();
    }

    
    /**
     * Test of POST method, of class MyResource.
     */
    @Test
    public void should_be_able_to_post_new_ingredient() {
        String json = "{ \"ingredient\":\"water\", \"volume\": { \"value\":\"300\", \"unit\":\"liter\" } }";
        target.request().post(Entity.entity(json, APPLICATION_JSON_TYPE));
//        String r = target.request().get(String.class);
//        assertEquals("[apple]", r);
    }

}