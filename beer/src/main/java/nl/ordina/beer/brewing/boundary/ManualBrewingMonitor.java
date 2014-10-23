package nl.ordina.beer.brewing.boundary;

import java.io.IOException;
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
import nl.ordina.beer.control.NotificationEvent;

@ServerEndpoint("/sockets/brewer/monitor")
public class ManualBrewingMonitor {

    private static final Logger log = Logger.getLogger(ManualBrewingMonitor.class.getName());
    private static final Set<Session> peers = synchronizedSet(new HashSet<>());

    Set<Session> getPeers() {
        return peers;
    }

    @OnOpen
    public void onOpen(Session client) {
        log.info(() -> String.format("MANUAL - CONNECTED: %s", client));
        peers.add(client);
    }

    @OnClose
    public void onClose(Session client) {
        log.info(() -> String.format("MANUAL - CLOSED: %s", client));
        peers.remove(client);
    }

    public void receive(@Observes NotificationEvent event) {
        log.info(() -> String.format("MANUAL - RECEIVED EVENT %s", event));
        peers.stream().forEach(peer -> send(peer, event.toJson()));

        final List<Session> closed = peers.stream()
                .filter(p -> !p.isOpen())
                .collect(toList());
        peers.removeAll(closed);
    }

    private void send(Session peer, JsonObject event) {
        if (peer.isOpen()) {
            try {
                peer.getBasicRemote().sendObject(event);
            } catch (IOException e) {
                log.log(WARNING, "Error writing to peer " + peer, e);
            } catch (EncodeException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
