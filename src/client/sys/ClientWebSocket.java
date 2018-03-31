package client.sys;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import javax.websocket.ClientEndpoint;

@ClientEndpoint()
public class ClientWebSocket {
  
  @OnOpen
  public void onOpen(Session session) {
      System.out.println("Connected to server");
  }

  @OnMessage
  public void onMessage(String message, Session session) {
     
  }

  @OnClose
  public void onClose() {
      
  }

}
