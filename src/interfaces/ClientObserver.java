package interfaces;

import data.EmotivData;

/**
 * Implementation of Observer Interface
 * 
 * @author Shilpa Bhat
 */
public interface ClientObserver {

  /**
   * Method that updates the observers.
   * 
   * @param data - the data received from server that has to passed to the observer.
   */
  void updateObserver(EmotivData data);
}
