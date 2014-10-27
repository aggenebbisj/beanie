package nl.ordina.beer.monitor.boundary;

import nl.ordina.beer.brewing.entity.BrewAction;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.Collections.synchronizedSet;

/**
 * This class describes a web socket endpoint for the path "/sockets/monitor". The configuration relies on a set of
 * custom encoders that are found in the concrete implementations of the BrewAction interface.
 * If you are new to web sockets, please visit http://docs.oracle.com/javaee/7/tutorial/doc/websocket.htm#GKJIQ5
 */
//TODO
public class EventMonitor {

    private static final Set<Session> peers = synchronizedSet(new HashSet<>());

    private transient Logger logger = Logger.getLogger(getClass().getName());

    /**
     * The session of the newly connected client is stored in an internal cache.
     *
     * @param client
     *         A Session object of the newly connected client
     */
    //TODO
    public void onOpen(Session client) {
    }

    /**
     * The session of a client is removed as soon as a connection closes.
     *
     * @param client
     *         A Session object of the closed connection
     */
    //TODO
    public void onClose(Session client) {
    }

    /**
     * This method implements a lambda that filters the peers for open connections and sends the action to each peer.
     * Moreover, it implements a lambda to filter and remove any closed connections to keep the cache clean and tidy. If
     * you are new to Lambda's, please have a look at http://docs.oracle
     * .com/javase/tutorial/collections/streams/index.html
     *
     * @param action
     *         Observes brew action executed event raised by the brewer.
     */
    //TODO
    public void observeBrewActions(BrewAction action) {
    }
}
