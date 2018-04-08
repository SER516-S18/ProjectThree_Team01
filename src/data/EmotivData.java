package data;

import org.json.JSONObject;

/**
 * This class represents the model we will be using to handle our Objects. It
 * has getters and setters for each object individually as well as getters,
 * setters and toString() for any of the sub objects. The client and the server
 * will both utilize this to provide a data model for the project.
 * 
 * @author Cephas Armstrong-Mensah
 *
 */
public class EmotivData {
  private JSONObject expressive;
  private JSONObject affective;
  private JSONObject performance;

  // Expressive JSON Object properties
  private double eyebrowRaise;
  private double eyebrowFurrow;
  private double timer;

  private double smile;
  private double clench;
  private double laugh;
  private double smerkRight;
  private double smerkLeft;

  private int lookingRight;
  private int lookingLeft;
  private int rightWink;
  private int leftWink;
  private int blink;

  // Affective JSON Object properties
  private double mediation;
  private double engagementBoredom;
  private double excitementShortTerm;
  private double frustration;
  private double excitementLongTerm;

  public EmotivData() {
    this.expressive = new JSONObject();
    this.affective = new JSONObject();

    this.eyebrowRaise = 0.0;
    this.eyebrowFurrow = 0.0;
    this.timer = 0.0;

    this.smerkRight = 0.0;
    this.smerkLeft = 0.0;
    this.smile = 0.0;
    this.clench = 0.0;
    this.laugh = 0.0;

    this.blink = 0;
    this.lookingLeft = 0;
    this.lookingRight = 0;
    this.rightWink = 0;
    this.leftWink = 0;

    this.mediation = 0.0;
    this.frustration = 0.0;
    this.excitementLongTerm = 0.0;
    this.excitementShortTerm = 0.0;
    this.engagementBoredom = 0.0;
  }

  public EmotivData(JSONObject jsonObject) {
    this.expressive = jsonObject.getJSONObject("Expressive");
    this.affective = jsonObject.getJSONObject("Affective");

    parseExpressive(this.expressive);
    parseAffective(this.affective);
  }

  public EmotivData(String strData) {
    this.toJson(strData);
  }

  public EmotivData(JSONObject expressive, JSONObject affective) {
    this.expressive = expressive;
    this.affective = affective;

    parseExpressive(this.expressive);
    parseAffective(this.affective);
  }

  public JSONObject getExpressive() {
    JSONObject expressive = new JSONObject();
    expressive.put("LookingRight", this.lookingRight);
    expressive.put("EyebrowRaise", this.eyebrowRaise);
    expressive.put("EyebrowFurrow", this.eyebrowFurrow);
    expressive.put("LookingLeft", this.lookingLeft);
    expressive.put("LookingDown", this.smerkRight);
    expressive.put("LookingUp", this.smerkLeft);
    expressive.put("RightWink", this.rightWink);
    expressive.put("LeftWink", this.leftWink);
    expressive.put("Blink", this.blink);
    expressive.put("Smile", this.smile);
    expressive.put("Clench", this.clench);
    expressive.put("Laugh", this.getLaugh());
    expressive.put("Timer", this.getTimer());
    return expressive;
  }

  public JSONObject getAffective() {
    JSONObject affective = new JSONObject();
    affective.put("Mediation", this.mediation);
    affective.put("EngagementBoredom", this.engagementBoredom);
    affective.put("ExcitementShortTerm", this.excitementShortTerm);
    affective.put("Frustration", this.frustration);
    affective.put("ExcitementLongTerm", this.excitementLongTerm);
    return affective;
  }

  public JSONObject toJson() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("Expressive", this.getExpressive());
    jsonObject.put("Affective", this.getAffective());
    return jsonObject;
  }

  public void toJson(String jsonString) {
    JSONObject json = new JSONObject(jsonString);
    this.expressive = json.getJSONObject("Expressive");
    this.affective = json.getJSONObject("Affective");

    this.parseExpressive(this.expressive);
    this.parseAffective(this.affective);
  }

  public int getLookingRight() {
    return lookingRight;
  }

  public int getRightWink() {
    return rightWink;
  }

  public int getLeftWink() {
    return leftWink;
  }

  public int getBlink() {
    return blink;
  }

  public int getLookingLeft() {
    return lookingLeft;
  }

  public double getLaugh() {
    return laugh;
  }

  public double getTimer() {
    return timer;
  }

  public double getEyebrowRaise() {
    return eyebrowRaise;
  }

  public double getEyebrowFurrow() {
    return eyebrowFurrow;
  }

  public double getSmerkLeft() {
    return smerkLeft;
  }

  public double getSmerkRight() {
    return smerkRight;
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
    this.parseExpressive(this.expressive);
  }

  public void setAffective(JSONObject affective) {
    this.affective = affective;
    this.parseAffective(this.affective);
  }

  public void setTimer(double timer) {
    this.timer = timer;
  }

  public void setEyebrowRaise(double eyebrowRaise) {
    this.eyebrowRaise = eyebrowRaise;
  }

  public void setEyebrowFurrow(double eyebrowFurrow) {
    this.eyebrowFurrow = eyebrowFurrow;
  }

  public void setSmerkLeft(double smerkLeft) {
    this.smerkLeft = smerkLeft;
  }

  public void setSmerkRight(double smerkRight) {
    this.smerkRight = smerkRight;
  }

  public void setLaugh(double laugh) {
    this.laugh = laugh;
  }

  public void setSmile(double smile) {
    this.smile = smile;
  }

  public void setClench(double clench) {
    this.clench = clench;
  }

  public void setLookingRight(int lookingRight) {
    this.lookingRight = lookingRight;
  }

  public void setLookingLeft(int lookingLeft) {
    this.lookingLeft = lookingLeft;
  }

  public void setRightWink(int rightWink) {
    this.rightWink = rightWink;
  }

  public void setLeftWink(int leftWink) {
    this.leftWink = leftWink;
  }

  public void setBlink(int blink) {
    this.blink = blink;
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

  public void setJSONObject(JSONObject jsonObject) {
    this.expressive = jsonObject.getJSONObject("Expressive");
    this.affective = jsonObject.getJSONObject("Affective");

    parseExpressive(this.expressive);
    parseAffective(this.affective);
  }

  public void resetExpressiveData() {
    this.lookingRight = 0;
    this.lookingLeft = 0;
    this.rightWink = 0;
    this.leftWink = 0;
    this.blink = 0;
  }

  private void parseExpressive(JSONObject expressive) {
    this.eyebrowRaise = expressive.getDouble("EyebrowRaise");
    this.eyebrowFurrow = expressive.getDouble("EyebrowFurrow");
    this.timer = expressive.getDouble("Timer");

    this.smerkRight = expressive.getDouble("LookingDown");
    this.smerkLeft = expressive.getDouble("LookingUp");
    this.smile = expressive.getDouble("Smile");
    this.clench = expressive.getDouble("Clench");
    this.laugh = expressive.getDouble("Laugh");

    this.blink = expressive.getInt("Blink");
    this.lookingLeft = expressive.getInt("LookingLeft");
    this.lookingRight = expressive.getInt("LookingRight");
    this.rightWink = expressive.getInt("RightWink");
    this.leftWink = expressive.getInt("LeftWink");
  }

  private void parseAffective(JSONObject affective) {
    this.mediation = affective.getDouble("Mediation");
    this.frustration = affective.getDouble("Frustration");
    this.excitementLongTerm = affective.getDouble("ExcitementLongTerm");
    this.excitementShortTerm = affective.getDouble("ExcitementShortTerm");
    this.engagementBoredom = affective.getDouble("EngagementBoredom");
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(toJson());
    return sb.toString();
  }
}