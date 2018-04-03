package client.sys;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;

import util.Constants;

public class ClientThread implements Runnable {

  public static volatile boolean isClosing = false;
  private String uri;

  public ClientThread(String uri) {
    this.uri = uri;
  }

  @Override
  public void run() {
    ClientManager client = ClientManager.createClient();
    try {
      client.connectToServer(ClientWebSocket.class, new URI(
          Constants.PROTOCOL + uri + ':' + Constants.PORT + Constants.LINK + Constants.ENDPOINT));
    } catch (DeploymentException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
