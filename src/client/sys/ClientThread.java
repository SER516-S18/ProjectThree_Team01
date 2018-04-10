package client.sys;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import client.gui.EmotivControlPanel;
import util.Constants;

/**
 * 
 * @author Shilpa Bhat
 * @version 1.0
 * @since 2018-04-02
 *
 * Thread that connects to the server,
 * waits till the client closes,
 * closes the session when the client closes.
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
  
  /**
   * Connect to server using the uri and port entered 
   * by the user.
   */
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
    	JOptionPane.showMessageDialog(new JFrame(), "Could not connect to the Composer!");
      throw new RuntimeException(e);
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}
