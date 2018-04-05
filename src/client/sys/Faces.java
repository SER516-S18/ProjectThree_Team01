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
	
	/**
	 * Default upperFace image
	 * @return ImageIcon.
	 */
	public static ImageIcon defaultUpperFace() {
		return new ImageIcon("img/upperFace_normal.png");
	}
	
	/**
	 * Default lowerFace image
	 * @return ImageIcon.
	 */
	public static ImageIcon defaultLoweFace() {
		return new ImageIcon("img/lowerFace_normal.png");
	}
	
	/**
	 * Logic for changing the upperFace Image based on the inputs
	 * @param blink.
	 * @param rightWink.
	 * @param leftWink.
	 * @param lookLeft.
	 * @param lookRight.
	 * @param furrow.
	 * @param raise.
	 * @return ImageIcon.
	 */
	public static ImageIcon upperFace(double blink, double rightWink, double leftWink, 
			double lookLeft, double lookRight, double furrow, double raise) {
		
		if(blink==1) {
			return new ImageIcon("img/blink.png");
		}else if(rightWink==1) {
			return new ImageIcon("img/wink_right.png");
		}else if(leftWink==1) {
			return new ImageIcon("img/wing_left.png");
		}else if(lookLeft==1) {
			return new ImageIcon("img/look_left.png");
		}else if(lookRight==1) {
			return new ImageIcon("img/look_right.png");
		}else if(furrow > .5) {
			return new ImageIcon("img/furrow.png");
		}else if(raise > .5) {
			return new ImageIcon("img/raise.png");
		}
		return defaultUpperFace();
	}
	
	/**
	 * Logic for changing the lowerFace Image based on the inputs
	 * @param smile.
	 * @param clench.
	 * @param leftSmirk.
	 * @param rightSmirk.
	 * @param laugh.
	 * @return ImageIcon
	 * 
	 */
	public static ImageIcon lowerFace(double smile, double clench, double leftSmirk, 
			double rightSmirk, double laugh) {
		if(smile > .5) {
			return new ImageIcon("img/smile.png");
		}else if(clench > .5) {
			return new ImageIcon("img/clench.png");
		}else if(leftSmirk > .5) {
			return new ImageIcon("img/smirk_left.png");
		}else if(rightSmirk > .5) {
			return new ImageIcon("img/smirk_right.png");
		}else if(laugh > .5) {
			return new ImageIcon("img/laugh.png");
		}
		return defaultLoweFace();
	}


}
