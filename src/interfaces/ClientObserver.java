package interfaces;

import data.EmotivData;

/**
 * 
 * @author Shilpa Bhat
 * Implementation of Observer Interface
 */
public interface ClientObserver {
  
  /**
   * Method that updates the observers.
   * @param data -  the data received from server
   * that has to passed to the observer.
   */
  void notifyObserver(EmotivData data);
}
