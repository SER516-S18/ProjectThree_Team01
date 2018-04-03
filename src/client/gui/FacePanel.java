package client.gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.sys.Faces;
import data.EmotivData;

/**
 * The FacePanel implements a JPanel to create a Panel for displaying 
 * different facial expressions
 * 
 * @author Karansher Bhangal
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-03
 *
 */
public class FacePanel extends JPanel{
	
	private JLabel JLabelUpperFace;
	private JLabel JLabelEyes;
	private JLabel JLabelLowerFace;
	
	public FacePanel() {
	    setBounds(0, 0, 450, 300);
	    setBackground(Color.white);
	    setLayout(null);
	    initialize();
	  }
	
	private void initialize() {
		
		JLabelUpperFace = new JLabel();
		JLabelUpperFace.setBackground(Color.BLUE);
		JLabelUpperFace.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelUpperFace.setBounds(6, 46, 217, 51);
		
		JLabelEyes = new JLabel();
		JLabelEyes.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelEyes.setBounds(6, 97, 217, 51);
		
		JLabelLowerFace = new JLabel();
		JLabelLowerFace.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelLowerFace.setBounds(6, 184, 217, 51);
    
	    add(JLabelUpperFace);
	    add(JLabelEyes);
	    add(JLabelLowerFace);
	    
	    setDefaultImage();
	  }
	
	private void setDefaultImage() {
		JLabelUpperFace.setIcon(Faces.defaultUpperFace());
		JLabelEyes.setIcon(Faces.defaultEyes());
		JLabelLowerFace.setIcon(Faces.defaultLoweFace());
	}
	
	public void updateFace(EmotivData emotiveData) {
		
		ImageIcon brows = Faces.upperFace(emotiveData.getEyebrowFurrow(), emotiveData.getEyebrowRaise());
		ImageIcon eyes = Faces.eyes(emotiveData.getBlink(), emotiveData.getRightWink(), 
				emotiveData.getLeftWink(), emotiveData.getLookingLeft(), emotiveData.getLookingRight());
//		ImageIcon mouth = faces.lowerFace(emotiveData.getSmile(), emotiveData.getClench(),
//				emotiveData.getLeftSmirk(), emotiveData.getRightSmirk(), emotiveData.getLaugh());
		
		JLabelUpperFace.setIcon(brows);
		JLabelEyes.setIcon(eyes);
		//JLabelLowerFace.setIcon(mouth);
	}
}
