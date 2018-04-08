package server.sys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import data.EmotivData;
import server.gui.EmotivComposer;
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
	        data.setClench(EmotivComposer.getemoFacialPanel().getClench());
	        break;
	      case "eyebrowraise":
	        data.setEyebrowRaise(EmotivComposer.getemoFacialPanel().getEyebrowRaise());
	        break;
	      case "smerkright":
	        data.setSmerkRight(EmotivComposer.getemoFacialPanel().getSmerkRight());
	        break;
	      case "smerkleft":
	        data.setSmerkLeft(EmotivComposer.getemoFacialPanel().getSmerkLeft());
	        break;
	      case "eyebrowfurrow":
	        data.setEyebrowFurrow(EmotivComposer.getemoFacialPanel().getEyebrowFurrow());
	        break;
	      case "laugh":
	        data.setLaugh(EmotivComposer.getemoFacialPanel().getLaugh());
	        break;
	      case "smile":
	        data.setSmile(EmotivComposer.getemoFacialPanel().getSmile());
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
    System.out.println("Interval: " + interval);
    this.interval = Double.parseDouble(interval);
    this.data.setTimer(data.getTimer() + this.interval);
    this.sendButtonText = sendButtonText;

    notifyObservers();
  }

  public void updateEyeAction(String key, int value) {
    this.data.resetExpressiveData();

    if (key.equals("Blink")) {
      data.setBlink(value);
    } else if (key.equals("LookingLeft")) {
      data.setLookingLeft(value);
    } else if (key.equals("LookingRight")) {
      data.setLookingRight(value);
    } else if (key.equals("LeftWink")) {
      data.setLeftWink(value);
    } else if (key.equals("RightWink")) {
      data.setRightWink(0);
    }
    notifyObservers();
    
    System.out.println("get new eye value: " + value);
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
      observer.updateAll(passedData);
    }
  }
}
