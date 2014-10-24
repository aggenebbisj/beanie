package nl.ordina.beer.monitor.boundary;

import java.io.IOException;
import static java.lang.String.format;
import static java.util.Collections.synchronizedSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javax.enterprise.event.Observes;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nl.ordina.beer.brewing.entity.BrewActionCompletedEvent;

@ServerEndpoint("/sockets/monitor")
public class EventMonitor {

    private transient Logger logger = Logger.getLogger(getClass().getName());
    private static final Set<Session> peers = synchronizedSet(new HashSet<>());

    Set<Session> getPeers() {
        return peers;
    }

    @OnOpen
    public void onOpen(Session client) {
        logger.info(() -> format("CONNECTED: %s", client));
        peers.add(client);
    }

    @OnClose
    public void onClose(Session client) {
        logger.info(() -> format("CLOSED: %s", client));
        peers.remove(client);
    }

    /**
     *
     * @param event brew completion event raised by the brewer.
     */
    public void receive(@Observes BrewActionCompletedEvent event) {
        logger.info(() -> format("RECEIVED EVENT %s", event));

        peers.stream()
                .filter(p -> p.isOpen())
                .forEach(p -> send(p, event.toJson()));

        final List<Session> closed = peers.stream()
                .filter(p -> !p.isOpen())
                .collect(toList());
        peers.removeAll(closed);
    }

    private void send(Session peer, JsonObject event) {
        try {
            peer.getBasicRemote().sendObject(event);
        } catch (IOException e) {
            logger.log(WARNING, "Error writing to peer " + peer, e);
        } catch (EncodeException e) {
            throw new IllegalStateException(e);
        }
    }

}
