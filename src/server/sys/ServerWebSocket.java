package server.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import data.EmotivData;
import util.Constants;

/**
 * The purpose of this class is to provide the websocket logic for the different
 * life cycles and other static methods to expose the websocket since all of the
 * interaction is from the server outwards.
 * 
 * @author Cephas Armstrong-Mensah
 *
 */

@ServerEndpoint(value = Constants.ENDPOINT)
public class ServerWebSocket {
  public EmotivData emotivData = new EmotivData();

  // Using a List so we can index and keep order
  static List<Session> clients = Collections.synchronizedList(new ArrayList<Session>());

  @OnOpen
  public void onOpen(Session session) {
    if (!clients.contains(session)) {
      clients.add(session);
      System.out.println(String.format("Welcome client: %s", session.getId()));
    }
  }

  @OnMessage
  public void onMesage(String emotivDataString, Session session) throws IOException {
    emotivData.toJson(emotivDataString);
  }

  @OnClose
  public void onClose(Session session) throws IOException {
    clients.remove(session);
    System.out.println(String.format("%s: disconnected", session.getId()));
  }

  @OnError
  public void onError(Session session, Throwable t) {
  }

  public static void sendMessage(Session session, String message) throws IOException {
    if (clients.contains(session))
      session.getBasicRemote().sendText(message);
    else
      throw new NullPointerException();
  }

  public static List<Session> getClients() {
    return clients;
  }
}
