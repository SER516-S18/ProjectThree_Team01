package server.sys;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import data.EmotivData;

@ServerEndpoint(value = "/composer")
public class ServerWebSocket {

  @OnOpen
  public void onOpen(Session session) {
  }

  @OnMessage
  public void onMesage(EmotivData emotivData, Session session) throws IOException, EncodeException {

  }

  @OnClose
  public void onClose() throws IOException, EncodeException {

  }

}
