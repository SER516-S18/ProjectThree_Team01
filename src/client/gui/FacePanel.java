package client.gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.sys.Faces;
import data.EmotivData;

/**
 * The FacePanel implements a JPanel to create a Panel for displaying different
 * facial expressions
 * 
 * @author Karansher Bhangal
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-03
 *
 */
public class FacePanel extends JPanel {

  private static final long serialVersionUID = -5762535688754954249L;
  private JLabel JLabelUpperFace;
  private JLabel JLabelLowerFace;

  public FacePanel() {
    setBounds(0, 0, 450, 300);
    setBackground(Color.white);
    setLayout(null);
    initialize();
  }

  /**
   * This method is used to set the labels for UpperFace and LowerFace
   *
   */
  private void initialize() {
    JLabelUpperFace = new JLabel();
    JLabelUpperFace.setBackground(Color.BLUE);
    JLabelUpperFace.setHorizontalAlignment(SwingConstants.CENTER);
    JLabelUpperFace.setBounds(0, 0, 450, 192);

    JLabelLowerFace = new JLabel();
    JLabelLowerFace.setHorizontalAlignment(SwingConstants.CENTER);
    JLabelLowerFace.setBounds(0, 192, 450, 192);

    add(JLabelUpperFace);
    add(JLabelLowerFace);

    setDefaultImage();
  }

  /**
   * This method is used to set the default Images
   *
   */
  private void setDefaultImage() {
    JLabelUpperFace.setIcon(Faces.defaultUpperFace());
    JLabelLowerFace.setIcon(Faces.defaultLoweFace());
  }

  public void updateFace(EmotivData emotiveData) {

    ImageIcon upperFaceImage = Faces.upperFace(emotiveData.getBlink(), emotiveData.getRightWink(),
        emotiveData.getLeftWink(), emotiveData.getLookingLeft(), emotiveData.getLookingRight(),
        emotiveData.getEyebrowFurrow(), emotiveData.getEyebrowRaise());
    ImageIcon lowerFaceImage = Faces.lowerFace(emotiveData.getSmile(), emotiveData.getClench(),
        emotiveData.getSmirkLeft(), emotiveData.getSmirkRight(), emotiveData.getLaugh());

    JLabelUpperFace.setIcon(upperFaceImage);
    JLabelLowerFace.setIcon(lowerFaceImage);
  }
}
