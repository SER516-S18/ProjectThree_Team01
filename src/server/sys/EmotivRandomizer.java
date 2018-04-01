package server.sys;

import java.util.Iterator;
import java.util.Random;

import data.EmotivData;

public class EmotivRandomizer {
  private EmotivData data;
  private Iterator<String> objs;
  private Random generator;

  public EmotivRandomizer() {
    data = new EmotivData();
    generator = new Random();
  }

  public EmotivData getRandomData() {
    randomizeAffective();
    randomizeExpressive();
    return data;
  }

  private void randomizeExpressive() {
    objs = data.getExpressive().keys();
    String strKey;
    while (objs.hasNext()) {
      strKey = objs.next();
      switch (strKey.toLowerCase()) {
      case "lookingright":
        data.setLookingRight(generator.nextDouble());
        break;
      case "clench":
        data.setClench(generator.nextDouble());
        break;
      case "eyebrowraise":
        data.setEyebrowRaise(generator.nextDouble());
        break;
      case "lookingleft":
        data.setLookingLeft(generator.nextDouble());
        break;
      case "lookingdown":
        data.setLookingDown(generator.nextDouble());
        break;
      case "lookingup":
        data.setLookingUp(generator.nextDouble());
        break;
      case "rightwink":
        data.setRightWink(generator.nextDouble());
        break;
      case "blink":
        data.setBlink(generator.nextDouble());
        break;
      case "eyebrowfurrow":
        data.setEyebrowFurrow(generator.nextDouble());
        break;
      case "eyesopen":
        data.setEyesOpen(generator.nextDouble());
        break;
      case "leftwink":
        data.setLeftWink(generator.nextDouble());
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
}
