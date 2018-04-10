package server.sys.observer;

import data.EmotivData;

/**
 * This class contains data being passed through the observer
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 07APR2018
 * 
 */
public class PassedData {
  public EmotivData data;
  public String buttonText;
  public double interval;
  public boolean interactiveAutoReset;
  public boolean isSent;
  public boolean facial;
  public boolean performance;

  public PassedData(EmotivData data, String buttonText, double interval, boolean interactiveAutoReset,
      boolean isSent, boolean facial, boolean performance) {
    this.data = data;
    this.buttonText = buttonText;
    this.interval = interval;
    this.interactiveAutoReset = interactiveAutoReset;
    this.isSent = isSent;
    this.facial = facial;
    this.performance = performance;
  }
}
