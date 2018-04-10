package client.sys;

import java.util.ArrayList;

import data.EmotivData;
import interfaces.ClientObservable;
import interfaces.ClientObserver;

/**
 * 
 * @author Shilpa Bhat
 * 
 * Observable - This class is the subject that is
 * observed to get data from the server.
 * Uses Singleton pattern so that the same instance
 * is used by websocket and the observers.
 */
public class ClientSubject implements ClientObservable {

  ArrayList<ClientObserver> observerList = new ArrayList<>();
  EmotivData emotivData;
  private static ClientSubject subjectInstance;
  
  //update the observer list about the incoming data.
  public void updateObservers(EmotivData data) {
    System.out.println("Are we here then: " + data.getExcitementLongTerm());
    emotivData = data;
    notifyObserver(observerList);
  }

  private ClientSubject() {
  }
  
  /**
   * @return the singleton instance of the class
   */
  public static ClientSubject getInstance() {
    if (subjectInstance == null) {
      subjectInstance = new ClientSubject();
    }
    return subjectInstance;
  }
  
  /*
   * (non-Javadoc)
   * @see interfaces.ClientObservable#addObserver(interfaces.ClientObserver)
   */
  @Override
  public void addObserver(ClientObserver observer) {
    observerList.add(observer);
  }
  
  /*
   * (non-Javadoc)
   * @see interfaces.ClientObservable#removeObserver(interfaces.ClientObserver)
   */
  @Override
  public void removeObserver(ClientObserver observer) {
    observerList.remove(observer);

  }
  
  /*
   * (non-Javadoc)
   * @see interfaces.ClientObservable#notifyObserver(java.util.ArrayList)
   */
  @Override
  public void notifyObserver(ArrayList<ClientObserver> observers) {
    for (ClientObserver obs : observers) {
      obs.updateObserver(emotivData);
    }
  }
}
