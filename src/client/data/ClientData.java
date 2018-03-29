package client.data;

import org.json.JSONObject;

public class ClientData {
  private JSONObject expressive;
  private JSONObject affective;

  // Expressive JSON Object properties
  private double lookingRight;
  private double eyebrowRaise;
  private double lookingLeft;
  private double lookingDown;
  private double rightWink;
  private double leftWink;
  private double blink;
  private double eyesOpen;
  private double smile;
  private double clench;

  // Affective JSON Object properties
  private double mediation;
  private double engagementBoredom;
  private double excitementShortTerm;
  private double frustration;
  private double excitementLongTerm;

  public ClientData() {
    this.expressive = new JSONObject();
    this.affective = new JSONObject();
  }

  public ClientData(JSONObject expressive, JSONObject affective) {
    this.expressive = expressive;
    this.affective = affective;
  }

  public JSONObject getExpressive() {
    return expressive;
  }

  public JSONObject getAffective() {
    return affective;
  }

  public double getLookingRight() {
    return lookingRight;
  }

  public double getEyebrowRaise() {
    return eyebrowRaise;
  }

  public double getLookingLeft() {
    return lookingLeft;
  }

  public double getLookingDown() {
    return lookingDown;
  }

  public double getRightWink() {
    return rightWink;
  }

  public double getLeftWink() {
    return leftWink;
  }

  public double getBlink() {
    return blink;
  }

  public double getEyesOpen() {
    return eyesOpen;
  }

  public double getSmile() {
    return smile;
  }

  public double getClench() {
    return clench;
  }

  public double getMediation() {
    return mediation;
  }

  public double getEngagementBoredom() {
    return engagementBoredom;
  }

  public double getExcitementShortTerm() {
    return excitementShortTerm;
  }

  public double getFrustration() {
    return frustration;
  }

  public double getExcitementLongTerm() {
    return excitementLongTerm;
  }

  public void setExpressive(JSONObject expressive) {
    this.expressive = expressive;
  }

  public void setAffective(JSONObject affective) {
    this.affective = affective;
  }

  public void setLookingRight(double lookingRight) {
    this.lookingRight = lookingRight;
  }

  public void setEyebrowRaise(double eyebrowRaise) {
    this.eyebrowRaise = eyebrowRaise;
  }

  public void setLookingLeft(double lookingLeft) {
    this.lookingLeft = lookingLeft;
  }

  public void setLookingDown(double lookingDown) {
    this.lookingDown = lookingDown;
  }

  public void setRightWink(double rightWink) {
    this.rightWink = rightWink;
  }

  public void setLeftWink(double leftWink) {
    this.leftWink = leftWink;
  }

  public void setBlink(double blink) {
    this.blink = blink;
  }

  public void setEyesOpen(double eyesOpen) {
    this.eyesOpen = eyesOpen;
  }

  public void setSmile(double smile) {
    this.smile = smile;
  }

  public void setClench(double clench) {
    this.clench = clench;
  }

  public void setMediation(double mediation) {
    this.mediation = mediation;
  }

  public void setEngagementBoredom(double engagementBoredom) {
    this.engagementBoredom = engagementBoredom;
  }

  public void setExcitementShortTerm(double excitementShortTerm) {
    this.excitementShortTerm = excitementShortTerm;
  }

  public void setFrustration(double frustration) {
    this.frustration = frustration;
  }

  public void setExcitementLongTerm(double excitementLongTerm) {
    this.excitementLongTerm = excitementLongTerm;
  }
}