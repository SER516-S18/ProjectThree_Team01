package interfaces;

import data.EmotivData;

public interface ClientObserver {
  void notifyObserver(EmotivData data);
}
