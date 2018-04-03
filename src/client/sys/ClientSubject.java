package client.sys;

import java.util.ArrayList;

import data.EmotivData;
import interfaces.ClientObservable;
import interfaces.ClientObserver;

public class ClientSubject implements ClientObservable {

  ArrayList<ClientObserver> observerList = new ArrayList<>();
  EmotivData emotivData;
  private static ClientSubject subjectInstance;

  public void updateObservers(EmotivData data) {
    emotivData = data;
    notifyObserver(observerList);
  }

  private ClientSubject() {
  }

  public static ClientSubject getInstance() {
    if (subjectInstance == null) {
      subjectInstance = new ClientSubject();
    }
    return subjectInstance;
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
    for (ClientObserver obs : observers) {
      obs.notifyObserver(emotivData);
    }
  }
}
