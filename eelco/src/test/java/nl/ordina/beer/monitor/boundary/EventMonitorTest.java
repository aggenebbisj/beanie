
package nl.ordina.beer.monitor.boundary;

import javax.websocket.Session;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventMonitorTest {

    private EventMonitor sut = new EventMonitor();
    
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
    
    @After
    public void tearDown() {
        sut.getPeers().clear();
    }
}
