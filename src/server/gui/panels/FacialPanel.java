package server.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import server.gui.actions.EmoAction;
import util.UpDownButton;

public class FacialPanel extends JPanel {

  private static final long serialVersionUID = 3981755002828308262L;

  private JLabel upperfaceLabel;
  private JComboBox<String> upperfaceComboBox;
  private UpDownButton upperfaceupdownButton;
  private JLabel lowerfaceLabel;
  private JComboBox<String> lowerfaceComboBox;
  private UpDownButton lowerfaceupdownButton;
  private JLabel eyeLabel;
  private JComboBox<String> eyecomboBox;
  private JRadioButton eyeActive;
  private JCheckBox chckbxNewCheckBox;

  private double lookingRight;
  private double eyebrowRaise;
  private double eyebrowFurrow;
  private double lookingLeft;
  private double lookingDown;
  private double lookingUp;
  private double rightWink;
  private double leftWink;
  private double blink;
  private double eyesOpen;
  private double smile;
  private double clench;
  private double laugh;

  private int eyeclickCount = 0;
  private Double eyeActiveValue;

  public FacialPanel() {
    setBounds(0, 0, 440, 150);
    initialize();
    setLayout(null);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    upperfaceupdownButton = new UpDownButton(70, 0.1, false);
    upperfaceupdownButton.setBounds(135, 30, 70, 30);
    upperfaceupdownButton.setVisible(true);

    lowerfaceupdownButton = new UpDownButton(70, 0.1, false);
    lowerfaceupdownButton.setBounds(365, 30, 70, 30);
    lowerfaceupdownButton.setVisible(true);

    upperfaceComboBox = new JComboBox<String>();

    upperfaceComboBox.addActionListener(new EmoAction(this, "upperfaceComboBox"));
    upperfaceComboBox.setBounds(10, 30, 125, 30);
    upperfaceComboBox
        .setModel(new DefaultComboBoxModel<String>(new String[] { "Raise Brow", "Furrow Brow" }));

    lowerfaceComboBox = new JComboBox<String>();

    /**
     * lowerfaceComboBox action
     */

    lowerfaceComboBox.addActionListener(new EmoAction(this, "lowerfaceComboBox"));

    lowerfaceComboBox.setBounds(240, 30, 125, 30);
    lowerfaceComboBox.setModel(new DefaultComboBoxModel<String>(
        new String[] { "Smile", "Clench", "Smirk Left", "Smirk Right", "Laugh" }));

    upperfaceLabel = new JLabel("Upperface:");
    upperfaceLabel.setBounds(10, 10, 100, 20);
    upperfaceLabel.setHorizontalAlignment(SwingConstants.LEFT);

    lowerfaceLabel = new JLabel("Lowerface:");
    lowerfaceLabel.setBounds(240, 10, 100, 20);
    lowerfaceLabel.setHorizontalAlignment(SwingConstants.LEFT);

    eyeLabel = new JLabel("Eye:");
    eyeLabel.setBounds(10, 80, 50, 20);
    eyeLabel.setHorizontalAlignment(SwingConstants.LEFT);

    eyecomboBox = new JComboBox<String>();
    eyecomboBox.setBounds(10, 100, 125, 30);
    eyecomboBox.setModel(new DefaultComboBoxModel<String>(
        new String[] { "Blink", "Wink Left", "Wink Right", "Look Left", "Look Right" }));

    eyeActive = new JRadioButton("Active");

    eyeActive.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        if (eyeclickCount % 2 == 0) {
          eyeActive.setActionCommand("1");
        } else {
          eyeActive.setActionCommand("0");
        }

        eyeActiveValue = Double.parseDouble(eyeActive.getActionCommand());

        eyeclickCount++;
        // System.out.println("eye sectioon:"+ eyeActiveValue); //test
      }
    });

    eyecomboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String Item = eyecomboBox.getSelectedItem().toString();
        eyeActiveValue = Double.parseDouble(eyeActive.getActionCommand());
        // System.out.println(eyeActiveValue);
        if (Item.equals("Blink")) {
          blink = eyeActiveValue;
          // System.out.println(blink +","+ Item); //test
        } else if (Item.equals("Wink Left")) {
          leftWink = eyeActiveValue;
          // System.out.println(leftWink +","+ Item); //test
        } else if (Item.equals("Wink Right")) {
          rightWink = eyeActiveValue;
          // System.out.println(rightWink +","+ Item); //test
        } else if (Item.equals("Look Left")) {
          lookingLeft = eyeActiveValue;
          System.out.println(lookingLeft + "," + Item); // test
        } else if (Item.equals("Look Right")) {
          lookingRight = eyeActiveValue;
          // System.out.println(lookingRight +","+ Item); //test
        } else {
        }
      }
    });

    eyeActive.setBounds(140, 105, 80, 20);

    chckbxNewCheckBox = new JCheckBox("Auto Reset");
    chckbxNewCheckBox.setBounds(270, 105, 115, 20);

    add(upperfaceupdownButton);
    add(upperfaceLabel);
    add(lowerfaceLabel);
    add(eyeLabel);
    add(upperfaceComboBox);
    add(eyecomboBox);
    add(lowerfaceComboBox);
    add(lowerfaceupdownButton);
    add(chckbxNewCheckBox);
    add(eyeActive);
  }

  public JComboBox<String> getUpperfaceComboBox() {
    return upperfaceComboBox;
  }

  public void setUpperfaceComboBox(JComboBox<String> upperfaceComboBox) {
    this.upperfaceComboBox = upperfaceComboBox;
  }

  public UpDownButton getUpperfaceupdownButton() {
    return upperfaceupdownButton;
  }

  public void setUpperfaceupdownButton(UpDownButton upperfaceupdownButton) {
    this.upperfaceupdownButton = upperfaceupdownButton;
  }

  public JLabel getLowerfaceLabel() {
    return lowerfaceLabel;
  }

  public void setLowerfaceLabel(JLabel lowerfaceLabel) {
    this.lowerfaceLabel = lowerfaceLabel;
  }

  public JComboBox<String> getLowerfaceComboBox() {
    return lowerfaceComboBox;
  }

  public void setLowerfaceComboBox(JComboBox<String> lowerfaceComboBox) {
    this.lowerfaceComboBox = lowerfaceComboBox;
  }

  public UpDownButton getLowerfaceupdownButton() {
    return lowerfaceupdownButton;
  }

  public void setLowerfaceupdownButton(UpDownButton lowerfaceupdownButton) {
    this.lowerfaceupdownButton = lowerfaceupdownButton;
  }

  public JComboBox<String> getEyecomboBox() {
    return eyecomboBox;
  }

  public void setEyecomboBox(JComboBox<String> eyecomboBox) {
    this.eyecomboBox = eyecomboBox;
  }

  public JRadioButton getEyeActive() {
    return eyeActive;
  }

  public void setEyeActive(JRadioButton eyeActive) {
    this.eyeActive = eyeActive;
  }

  public double getLookingRight() {
    return lookingRight;
  }

  public void setLookingRight(double lookingRight) {
    this.lookingRight = lookingRight;
  }

  public double getEyebrowRaise() {
    return eyebrowRaise;
  }

  public void setEyebrowRaise(double eyebrowRaise) {
    this.eyebrowRaise = eyebrowRaise;
  }

  public double getEyebrowFurrow() {
    return eyebrowFurrow;
  }

  public void setEyebrowFurrow(double eyebrowFurrow) {
    this.eyebrowFurrow = eyebrowFurrow;
  }

  public double getLookingLeft() {
    return lookingLeft;
  }

  public void setLookingLeft(double lookingLeft) {
    this.lookingLeft = lookingLeft;
  }

  public double getLookingDown() {
    return lookingDown;
  }

  public void setLookingDown(double lookingDown) {
    this.lookingDown = lookingDown;
  }

  public double getLookingUp() {
    return lookingUp;
  }

  public void setLookingUp(double lookingUp) {
    this.lookingUp = lookingUp;
  }

  public double getRightWink() {
    return rightWink;
  }

  public void setRightWink(double rightWink) {
    this.rightWink = rightWink;
  }

  public double getLeftWink() {
    return leftWink;
  }

  public void setLeftWink(double leftWink) {
    this.leftWink = leftWink;
  }

  public double getBlink() {
    return blink;
  }

  public void setBlink(double blink) {
    this.blink = blink;
  }

  public double getSmile() {
    return smile;
  }

  public void setSmile(double smile) {
    this.smile = smile;
  }

  public double getClench() {
    return clench;
  }

  public void setClench(double clench) {
    this.clench = clench;
  }

  public double getLaugh() {
    return laugh;
  }

  public void setLaugh(double laugh) {
    this.laugh = laugh;
  }

  public int getEyeclickCount() {
    return eyeclickCount;
  }

  public void setEyeclickCount(int eyeclickCount) {
    this.eyeclickCount = eyeclickCount;
  }

  public Double getEyeActiveValue() {
    return eyeActiveValue;
  }

  public void setEyeActiveValue(Double eyeActiveValue) {
    this.eyeActiveValue = eyeActiveValue;
  }

}
