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

import data.EmotivDataTest;
import util.ConstantsTest;

@ServerEndpoint(value = ConstantsTest.ENDPOINT)
public class ServerWebSocket {
  public EmotivDataTest emotivData = new EmotivDataTest();

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
