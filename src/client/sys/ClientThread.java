package client.sys;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;

import util.ConstantsTest;

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
      client.connectToServer(ClientWebSocketTest.class, new URI(ConstantsTest.PROTOCOL + uri + ':'
          + ConstantsTest.PORT + ConstantsTest.LINK + ConstantsTest.ENDPOINT));
    } catch (DeploymentException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

}
