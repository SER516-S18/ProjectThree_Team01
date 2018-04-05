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
}
