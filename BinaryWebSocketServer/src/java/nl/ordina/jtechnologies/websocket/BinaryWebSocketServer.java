package nl.ordina.jtechnologies.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/BinaryWebSocket/binaryWebSocket.html?cid=8784&ssid=116111557024846
 * 
 * BinairyWebSocketServer
 * 
 * Inplaats van de gebruikelijke tekst chat applicatie wordt er hier gebruik gemaakt 
 * van een ByteBuffer om binaire data uit te wisselen
 */
@ServerEndpoint("/images")
public class BinaryWebSocketServer {

    /**
     * Set van sessions, voor iedere client die een sessie opend met deze ServerEndpoint 
     * komt er een sessie in deze set.
     */
    private static final Set<Session> sessions
            = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Afhandeling van een websocket client request
     * 
     * @param byteBuffer de binaire data die door een websocket client is opgestuurd 
     */
    @OnMessage
    public void onMessage(ByteBuffer byteBuffer) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendBinary(byteBuffer);
            } catch (IOException ex) {
                Logger.getLogger(BinaryWebSocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 
     * @param session de nieuwe sessie 
     */
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    /**
     * 
     * @param session de geslote sessie 
     */
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}
