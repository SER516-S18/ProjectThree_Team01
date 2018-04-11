package server.sys;

import java.util.ArrayList;
import java.util.List;

import data.EmotivData;
import server.sys.observer.EmotivObserver;
import server.sys.observer.EmotivSubject;
import server.sys.observer.PassedData;

/**
 * The purpose of this class is serve as the Subject for the Observer Pattern and is
 * responsible for the data manipulations prior to sending to the client
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 02APR2018
 * 
 */
public class SubjectImplementation implements EmotivSubject {
  private EmotivData data;

  private List<EmotivObserver> observers = new ArrayList<EmotivObserver>();

  private String sendButtonText;
  private double interval;
  private boolean interactiveResetButton;
  private boolean isSent;
  private boolean facial;
  private boolean performance;

  public SubjectImplementation() {
    data = new EmotivData();
    interval = 0.0;
    sendButtonText = "Send";
    interactiveResetButton = false;
    isSent = false;
  }

  public void setEmotivData(EmotivData data) {
    this.data = data;
    notifyObservers();
  }

  public EmotivData getEmotivData() {
    return this.data;
  }

  public void sendButtonText(String sendButtonText, String interval) {
    System.out.println("Interval: " + interval);
    this.interval = Double.parseDouble(interval);
    this.data.setTimer(data.getTimer() + this.interval);
    this.sendButtonText = sendButtonText;

    notifyObservers();
  }

  public void setInteractiveReset(boolean isAutoResetChecked) {
    this.interactiveResetButton = isAutoResetChecked;

    notifyObservers();
  }

  public void setIsSent(boolean isSent) {
    this.isSent = isSent;

    notifyObservers();
  }

  public void updateFacialPanel(boolean facial) {
    this.facial = facial;

    notifyObservers();
  }

  public void updatePerformance(boolean performance) {
    this.performance = performance;

    notifyObservers();
  }

  @Override
  public void addToObserver(EmotivObserver o) {
    observers.add(o);
  }

  @Override
  public void removeFromObserver(EmotivObserver o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    PassedData passedData = new PassedData(data, sendButtonText, interval, interactiveResetButton,
        isSent, facial, performance);
    for (EmotivObserver observer : observers) {
      observer.update(passedData);
    }
    isSent = false;
  }
}
