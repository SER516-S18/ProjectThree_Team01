package client.sys;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import data.EmotivData;

import javax.websocket.ClientEndpoint;

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
  }

  @OnClose
  public void onClose() {
      
  }

}
