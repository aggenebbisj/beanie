package nl.ordina.beer.monitor.boundary;

import nl.ordina.beer.brewing.entity.BrewAction;

import javax.enterprise.event.Observes;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.Collections.synchronizedSet;
import static java.util.logging.Level.WARNING;
import static java.util.stream.Collectors.toList;

@ServerEndpoint("/sockets/monitor")
public class EventMonitor {

    private transient Logger logger = Logger.getLogger(getClass().getName());
    private static final Set<Session> peers = synchronizedSet(new HashSet<>());

    Set<Session> getPeers() {
        return peers;
    }

    @OnOpen
    public void onOpen(Session client) {
        logger.finest(() -> format("CONNECTED: %s", client));
        peers.add(client);
    }

    @OnClose
    public void onClose(Session client) {
        logger.finest(() -> format("CLOSED: %s", client));
        peers.remove(client);
    }

    /**
     *
     * @param action Observes brew action executed event raised by the brewer.
     */
    public void receive(@Observes BrewAction action) {
        logger.finest(() -> format("RECEIVED EVENT %s", action));

        peers.stream()
                .filter(p -> p.isOpen())
                .forEach(p -> send(p, action.toJson()));

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
