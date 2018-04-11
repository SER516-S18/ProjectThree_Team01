package interfaces;

import java.util.ArrayList;

/**
 * 
 * Implementation of Observable, to be implemented in class that receives data and has to
 * notify observers.
 * 
 * @author Shilpa Bhat
 */
public interface ClientObservable {

  /**
   * Adds observer to the list of observers to notify.
   * 
   * @param observer - the observer to be added.
   */
  void addObserver(ClientObserver observer);

  /**
   * Removes observer to the list of observers to notify.
   * 
   * @param observer - observer to be removed from the list.
   */
  void removeObserver(ClientObserver observer);

  /**
   * @param observer -notify the list of clientobservers when data is received from the
   *        server.
   */
  void notifyObserver(ArrayList<ClientObserver> observer);
}
