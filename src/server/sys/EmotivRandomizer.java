package server.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
public class EmotivRandomizer implements EmotivSubject {
  private EmotivData data;
  private Iterator<String> objs;
  private Random generator;

  private List<EmotivObserver> observers = new ArrayList<EmotivObserver>();

  private String sendButtonText;
  private double interval;
  private boolean interactiveResetButton;
  private boolean isSent;

  public EmotivRandomizer() {
    data = new EmotivData();
    interval = 0.0;
    generator = new Random();
    sendButtonText = "Send";
    interactiveResetButton = false;
    isSent = false;
  }

  public void getRandomData() {
    randomizeExpressive();
    randomizeAffective();
    randomizePerformanceMetrics();
    notifyObservers();
  }

  // This will be removed later, leaving it here for testing purposes
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
      case "smirkright":
        data.setSmirkRight(generator.nextDouble());
        break;
      case "smirkleft":
        data.setSmirkLeft(generator.nextDouble());
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

  // This will be removed later, leaving it here for testing purposes
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

  // This will be removed later, leaving it here for testing purposes
  private void randomizePerformanceMetrics() {
    objs = data.getPerformance().keys();
    String strKey;
    while (objs.hasNext()) {
      strKey = objs.next();
      switch (strKey.toLowerCase()) {
      case "interest":
        data.setInterest(generator.nextDouble());
        break;
      case "engagement":
        data.setEngagement(generator.nextDouble());
        break;
      case "stress":
        data.setStress(generator.nextDouble());
        break;
      case "relaxation":
        data.setRelaxation(generator.nextDouble());
        break;
      case "excitement":
        data.setExcitement(generator.nextDouble());
        break;
      case "focus":
        data.setFocus(generator.nextDouble());
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

  public void setInteractiveReset(boolean isAutoResetChecked) {
    this.interactiveResetButton = isAutoResetChecked;

    notifyObservers();
  }

  public void setIsSent(boolean isSent) {
    this.isSent = isSent;

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
        isSent);
    for (EmotivObserver observer : observers) {
      observer.update(passedData);
    }
    isSent = false;
  }
}
