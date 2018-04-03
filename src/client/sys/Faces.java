package client.sys;

import javax.swing.ImageIcon;

/**
 * The Faces class sets the facial expressions based on the input values
 * 
 * @author Karansher Bhangal
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-03
 *
 */
public class Faces {
	
	public static ImageIcon defaultUpperFace() {
		return new ImageIcon("img/brow_normal.png");
	}
	
	public static ImageIcon defaultEyes() {
		return new ImageIcon("img/eyes_normal.png");
	}
	
	public static ImageIcon defaultLoweFace() {
		return new ImageIcon("img/mouth_normal.png");
	}
	
	public static ImageIcon upperFace(double furrow, double raise) {
		if(furrow > .1) {
			return new ImageIcon("img/brow_angry.png");
		}else if(raise > .1){
			return new ImageIcon("img/brow_raise.png");
		}
		return defaultUpperFace();
	}
	
	public static ImageIcon eyes(double blink, double rightWink, double leftWink, double lookLeft, double lookRight) {
		if(blink==1) {
			return new ImageIcon("img/eyes_blink.png");
		}else if(rightWink==1) {
			return new ImageIcon("img/eyes_wink_right.png");
		}else if(leftWink==1) {
			return new ImageIcon("img/eyes_wing_left.png");
		}else if(lookLeft==1) {
			return new ImageIcon("img/eyes_left.png");
		}else if(lookRight==1) {
			return new ImageIcon("img/eyes_right.png");
		}
		return defaultEyes();
	}
	
	public static ImageIcon lowerFace(double smile, double clench, double leftSmirk, double rightSmirk, double laugh) {
		if(smile > .5) {
			return new ImageIcon("img/mouth_smile.png");
		}else if(clench > .5) {
			return new ImageIcon("img/mouth_clengh.png");
		}else if(leftSmirk > .1) {
			return new ImageIcon("img/mouth_smirk_left.png");
		}else if(rightSmirk > .1) {
			return new ImageIcon("img/mouth_smirk_right.png");
		}else if(laugh > .1) {
			return new ImageIcon("img/mouth_laugh.png");
		}
		return defaultLoweFace();
	}

}
