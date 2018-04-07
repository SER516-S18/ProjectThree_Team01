package server.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import data.EmotivData;
import server.sys.observer.EmotivObserver;
import server.sys.observer.EmotivSubject;

public class EmotivRandomizer implements EmotivSubject {
  private static EmotivData data;
  private Iterator<String> objs;
  private Random generator;
  private static double interval;

  private List<EmotivObserver> observers = new ArrayList<EmotivObserver>();
  private String sendButtonText;

  public EmotivRandomizer() {
    data = new EmotivData();
    interval = 0.0;
    generator = new Random();
  }

  public void getRandomData() {
    randomizeAffective();
    randomizeExpressive();
    notifyObservers();
  }

  private void randomizeExpressive() {
    objs = data.getExpressive().keys();
    String strKey;
    while (objs.hasNext()) {
      strKey = objs.next();
      switch (strKey.toLowerCase()) {
      case "clench":
        data.setClench(generator.nextDouble());
        break;
      case "eyebrowraise":
        data.setEyebrowRaise(generator.nextDouble());
        break;
      case "smerkright":
        data.setSmerkRight(generator.nextDouble());
        break;
      case "smerkleft":
        data.setSmerkLeft(generator.nextDouble());
        break;
      case "eyebrowfurrow":
        data.setEyebrowFurrow(generator.nextDouble());
        break;
      case "laugh":
        data.setLaugh(generator.nextDouble());
        break;
      case "smile":
        data.setSmile(generator.nextDouble());
        break;
      default:
        break;
      }
    }
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

  public void sendButtonText(String sendButtonText, String interval) {
    double val = data.getTimer() + Double.parseDouble(interval);
    this.data.setTimer(val);
    this.sendButtonText = sendButtonText;

    notifyObservers();
  }

  @Override
  public void notifyObservers() {
    for (EmotivObserver observer : observers) {
      observer.updateAll(data, interval, sendButtonText);
    }
  }

  @Override
  public void addToObserver(EmotivObserver o) {
    observers.add(o);
  }

  @Override
  public void removeFromObserver(EmotivObserver o) {
    observers.remove(o);
  }
}
