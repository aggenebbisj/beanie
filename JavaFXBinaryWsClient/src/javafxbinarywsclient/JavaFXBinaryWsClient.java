package javafxbinarywsclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/BinaryWebSocket/binaryWebSocket.html?cid=8784&ssid=116111557024846
 * 
 * JavaFX websocket client.
 * 
 * Let Op! In de uitwerking op de oracle site zitten twee bugs. 
 * 
 * 1. Als je de stukjes code copieerd krijg je een
 *     private ImageView ImageView; (2 maal een I)
 * 
 * 2. in de onMessage wordt direct de imageView van een nieuw immage voorzien.
 *  dit geeft echter een :
 *  java.lang.IllegalStateException: Not on FX application thread; currentThread = Grizzly(2)
 *  dit komt omdat de websocket onmessgae in een andere thread wordt uitgevoerd als de JavaFX UI thread.
 *  Om dit op te lossen is er de runnable nodig zodat dit later op de juiste JavaFX thread kan worden uitgevoerd.
 * 
 */
@ClientEndpoint
public class JavaFXBinaryWsClient extends Application {

    private Session session;
    private ImageView imageView;
    private static final Logger LOGGER = Logger.getLogger(JavaFXBinaryWsClient.class.getName());

    @Override
    public void start(Stage primaryStage) {
        connectToWebSocket();
        Button btn = new Button();
        btn.setText("Send Image!");
        btn.setPrefSize(400, 27);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectAndSendImage(primaryStage);
            }
        });

        imageView = new ImageView();
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        AnchorPane root = new AnchorPane();

        AnchorPane.setTopAnchor(btn, 0.0);
        AnchorPane.setLeftAnchor(btn, 0.0);
        AnchorPane.setRightAnchor(btn, 0.0);
        AnchorPane.setTopAnchor(imageView, 27.0);
        AnchorPane.setBottomAnchor(imageView, 0.0);
        AnchorPane.setLeftAnchor(imageView, 0.0);
        AnchorPane.setRightAnchor(imageView, 0.0);

        root.getChildren().add(btn);
        root.getChildren().add(imageView);

        Scene scene = new Scene(root, 400, 427);

        primaryStage.setTitle("Image push!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void connectToWebSocket() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create("ws://localhost:8080/BinaryWebSocketServer/images");
            container.connectToServer(this, uri);
        } catch (DeploymentException | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }

    private void selectAndSendImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image to Send");
        File file = fileChooser.showOpenDialog(stage);
        try (InputStream input = new FileInputStream(file);
                OutputStream output = session.getBasicRemote().getSendStream()) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = input.read(buffer)) > 0) {
                output.write(buffer, 0, read);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(final InputStream input) {
        System.out.println("WebSocket message Received!");
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Image image = new Image(input);
                imageView.setImage(image);
            }
        };
        Platform.runLater(r);
    }

    @OnClose
    public void onClose() {
        connectToWebSocket();
    }
}
