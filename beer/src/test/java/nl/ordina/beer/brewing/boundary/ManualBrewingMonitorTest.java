
package nl.ordina.beer.brewing.boundary;

import nl.ordina.beer.brewing.boundary.ManualBrewingMonitor;
import javax.websocket.Session;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ManualBrewingMonitorTest {

    private ManualBrewingMonitor sut = new ManualBrewingMonitor();
    
    @Mock
    private Session client;
    
    @Test
    public void should_add_peer_on_open() {
        sut.onOpen(client);
        assertTrue(sut.getPeers().contains(client));
    }
    
    @Test
    public void should_remove_peer_on_close() {
        sut.onOpen(client);
        sut.onClose(client);
        assertTrue(sut.getPeers().isEmpty());
    }
    
//    @Test
//    public void should_send_message_to_peers_when_ingredient_added() {
//        final Ingredient ingredient = new Ingredient("Water", new Volume(300, LITER));
//        Mockito.when(client.isOpen()).thenReturn(true);
//        sut.onOpen(client);
//        final IngredientAddedEvent event = new IngredientAddedEvent(ingredient, new Kettle());
//        sut.receive(event);
//        Mockito.verify(client).getBasicRemote().sendObject(event);
//    }
    
    @After
    public void tearDown() {
        sut.getPeers().clear();
    }
}
