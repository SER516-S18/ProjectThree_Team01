package client.sys;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import data.EmotivData;

/**
 * Provides the websocket logic for the different
 * life cycles by including methods on opening session, receiving 
 * messages from server, closing the session etc
 * 
 * @author Shilpa Bhat
 * @version 1.0
 * @since 2018-04-01 
 *
 */
@ClientEndpoint()
public class ClientWebSocket {

  EmotivData emotivData = new EmotivData();

  @OnOpen
  public void onOpen(Session session) {
    System.out.println("Connected to server");
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    emotivData.toJson(message);
    ClientSubject.getInstance().updateObservers(emotivData);
  }

  @OnClose
  public void onClose() {
    System.out.println("Closing initiated from remote end...");
  }
}
