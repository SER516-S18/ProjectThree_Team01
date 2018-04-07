package server.sys.observer;

import data.EmotivData;

public interface EmotivObserver {
  public void updateAll(EmotivData data, double interval, String sendButtonText);

}
