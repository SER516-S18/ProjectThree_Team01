package client.sys;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import data.EmotivData;
import server.sys.*;
import interfaces.*;

import java.util.*;

import javax.websocket.ClientEndpoint;

@ClientEndpoint()
public class ClientWebSocket implements ClientObservable{
  
  EmotivData emotivData = new EmotivData();
  ArrayList<ClientObserver> observerList = new ArrayList<>();
  
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

  @Override
  public void addObserver(ClientObserver observer) {
    observerList.add(observer);
    
  }

  @Override
  public void removeObserver(ClientObserver observer) {
    observerList.remove(observer);
    
  }

  @Override
  public void notifyObserver(ArrayList<ClientObserver> observers) {
    for(ClientObserver obs: observers ) {
      obs.notify();
  }
    
  }

 

}
