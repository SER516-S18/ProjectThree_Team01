package server.sys.observer;

public interface EmotivSubject {
  public void addToObserver(EmotivObserver o);

  public void removeFromObserver(EmotivObserver o);

  public void notifyObservers();
}
