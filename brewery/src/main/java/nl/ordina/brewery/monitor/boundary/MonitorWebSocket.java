package nl.ordina.brewery.monitor.boundary;

import nl.ordina.brewery.entity.MonitorableEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.json.JsonObject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.Collections.synchronizedSet;
import static java.util.logging.Level.*;
import static java.util.stream.Collectors.toList;

@ServerEndpoint("/brewer")
@ApplicationScoped
public class MonitorWebSocket {
  private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass().getName());
  private static final Set<Session> peers = synchronizedSet(new HashSet<>());

  @OnOpen
  public void open(Session client) {
    log.log(INFO, "CONNECTED: {0}", client);
    peers.add(client);
  }

  @OnClose
  public void close(Session client) {
    log.log(INFO, "CLOSED: {0}", client);
    peers.remove(client);
  }

  @OnMessage
  public void message(String msg, Session peer) throws IOException {
    log.log(INFO, "Interesting, received message {0} from {1}, will do NOTHING!", new Object[]{msg, peer});
  }

  public void receive(@Observes MonitorableEvent event) {
    log.log(FINER, "Received event {0}", event);
    peers.stream().forEach(p -> send(p, event.createJson()));

    final List<Session> closed = peers.stream().filter(p -> !p.isOpen()).collect(toList());
    peers.removeAll(closed);
  }

  private void send(Session p, JsonObject json) {
    if (p.isOpen())
      try {
        p.getBasicRemote().sendObject(json);
      } catch (IOException e) {
        log.log(WARNING, "Error writing to peer " + p, e);
      } catch (EncodeException e) {
        throw new IllegalStateException(e);
      }
  }

}
