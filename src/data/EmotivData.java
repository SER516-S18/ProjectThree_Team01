package data;

import org.json.JSONObject;

/**
 * This class represents the model we will be using to handle our Objects.
 * It has getters and setters for each object individually as well as getters,
 * setters and toString() for any of the sub objects.
 * The client and the server will both utilize this to provide a data model for
 * the project.
 * 
 * @author Cephas Armstrong-Mensah
 *
 */
public class EmotivData {
	private JSONObject expressive;
	private JSONObject affective;

	// Expressive JSON Object properties
	private double lookingRight;
	private double eyebrowRaise;
	private double lookingLeft;
	private double lookingDown;
	private double lookingUp;
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

	public EmotivData() {
		this.expressive = new JSONObject();
		this.affective = new JSONObject();
		
		this.lookingDown = 0.0;
		this.lookingLeft = 0.0;
		this.lookingRight = 0.0;
		this.lookingUp = 0.0;
		this.eyebrowRaise = 0.0;
		this.eyesOpen = 0.0;
		this.rightWink = 0.0;
		this.leftWink = 0.0;
		this.smile = 0.0;
		this.clench = 0.0;
		this.blink = 0.0;
		
		this.mediation = 0.0;
		this.frustration = 0.0;
		this.excitementLongTerm = 0.0;
		this.excitementShortTerm = 0.0;
		this.engagementBoredom = 0.0;
	}

  public EmotivData (JSONObject jsonObject) {
    this.expressive = jsonObject.getJSONObject("Expressive");
    this.affective = jsonObject.getJSONObject("Affective");
    
    parseExpressive(this.expressive);
    parseAffective(this.affective);
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
    expressive.put("LookingLeft", this.lookingLeft);
    expressive.put("LookingDown", this.lookingDown);
    expressive.put("LookingUp", this.lookingUp);
    expressive.put("RightWink", this.rightWink);
    expressive.put("LeftWink", this.leftWink);
    expressive.put("Blink", this.blink);
    expressive.put("EyesOpen", this.eyesOpen);
    expressive.put("Smile", this.smile);
    expressive.put("Clench", this.clench);	  
		return expressive;
	}

	public JSONObject getAffective() {
	  JSONObject affective = new JSONObject();
    expressive.put("Mediation", this.mediation);
    expressive.put("EngagementBoredom", this.engagementBoredom);
    expressive.put("ExcitementShortTerm", this.excitementShortTerm);
    expressive.put("Frustration", this.frustration);
    expressive.put("ExcitementLongTerm", this.excitementLongTerm);    
		return affective;
	}

  public JSONObject toJson () {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("Expressive", this.getExpressive());
    jsonObject.put("Affective", this.getAffective());  
    return jsonObject;
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
		this.parseExpressive(this.expressive);
	}

	public void setAffective(JSONObject affective) {
		this.affective = affective;
		this.parseAffective(this.affective);
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

  public void setJSONObject (JSONObject jsonObject) {
    this.expressive = jsonObject.getJSONObject("Expressive");
    this.affective = jsonObject.getJSONObject("Affective");
    
    parseExpressive(this.expressive);
    parseAffective(this.affective);
  }
 
  private void parseExpressive (JSONObject expressive) {
    this.lookingDown = expressive.getDouble("LookingDown");
    this.lookingLeft = expressive.getDouble("LookingLeft");
    this.lookingRight = expressive.getDouble("LookingRight");
    this.lookingUp = expressive.getDouble("LookingUp");
    this.eyebrowRaise = expressive.getDouble("EyebrowRaise");
    this.eyesOpen = expressive.getDouble("EyesOpen");
    this.rightWink = expressive.getDouble("RightWink");
    this.leftWink = expressive.getDouble("LeftWink");
    this.smile = expressive.getDouble("Smile");
    this.clench = expressive.getDouble("Clench");
    this.blink = expressive.getDouble("Blink");
  }
  
  private void parseAffective(JSONObject affective) {
    this.mediation = affective.getDouble("Mediation");
    this.frustration = affective.getDouble("Frustration");
    this.excitementLongTerm = affective.getDouble("ExcitementLongTerm");
    this.excitementShortTerm = affective.getDouble("ExcitementShortTerm");
    this.engagementBoredom = affective.getDouble("EngagementBoredom");
  }

  public String toString() {
    StringBuilder sb = new StringBuilder ();
    sb.append(this.toJson());
    return sb.toString();
  }
}