package server.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import data.EmotivData;
import server.sys.observer.EmotivObserver;
import server.sys.observer.EmotivSubject;
import server.sys.observer.PassedData;

public class EmotivRandomizer implements EmotivSubject {
  private EmotivData data;
  private Iterator<String> objs;
  private Random generator;
  private double interval;

  private List<EmotivObserver> observers = new ArrayList<EmotivObserver>();
  private String sendButtonText;
  private PassedData passedData;

  public EmotivRandomizer() {
    data = new EmotivData();
    interval = 0.0;
    generator = new Random();
    sendButtonText = "Send";
  }

  public void getRandomData() {
    randomizeAffective();
    notifyObservers();
  }

  private void randomizeAffective() {
    objs = data.getAffective().keys();
    String strKey;
    while (objs.hasNext()) {
      strKey = objs.next();
      switch (strKey.toLowerCase()) {
      case "engagementboredom":
        data.setEngagementBoredom(generator.nextDouble());
        break;
      case "excitementshortterm":
        data.setExcitementShortTerm(generator.nextDouble());
        break;
      case "mediation":
        data.setMediation(generator.nextDouble());
        break;
      case "frustriation":
        data.setFrustration(generator.nextDouble());
        break;
      case "excitementlongterm":
        data.setExcitementLongTerm(generator.nextDouble());
        break;
      default:
        break;
      }
    }
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

  public String getSendButtonText() {
    return this.sendButtonText;
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
    passedData = new PassedData(data, sendButtonText, interval);
    for (EmotivObserver observer : observers) {
      observer.update(passedData);
    }
  }
}
