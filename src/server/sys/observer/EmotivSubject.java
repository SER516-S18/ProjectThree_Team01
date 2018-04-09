package server.sys.observer;

/**
 * This interface is used for the Observer Pattern implemented in the back end
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 07APR2018
 * 
 */
public interface EmotivSubject {
  public void addToObserver(EmotivObserver o);

  public void removeFromObserver(EmotivObserver o);

  public void notifyObservers();
}
