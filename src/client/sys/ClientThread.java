package client.sys;

import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import client.gui.EmotivControlPanel;
import util.Constants;

/**
 * 
 * @author Shilpa Bhat
 * 
 *
 */
public class ClientThread implements Runnable {

  public static volatile boolean isClosing = false;
  private String uri;
  private int port;
  
  private EmotivControlPanel emoInstance;
  
  public ClientThread(String uri, int port) {
    this.uri = uri;
    this.port = port;
    emoInstance = EmotivControlPanel.getInstance();
  }

  @Override
  public void run() {
    ClientManager client = ClientManager.createClient();
    try {
      Session session =client.connectToServer(ClientWebSocket.class, new URI(
          Constants.PROTOCOL + uri + ':' + port + Constants.LINK + Constants.ENDPOINT));
      while(!emoInstance.getIsClosing()) {
    	  
      }
      session.close();
    } catch (DeploymentException | URISyntaxException e) {
      throw new RuntimeException(e);
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}
