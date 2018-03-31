package server.gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import util.UpDownButton;

public class Express extends JPanel {

  /**
   * 
   */
  private static final long serialVersionUID = 3981755002828308262L;

  private JComboBox<String> comboBox;
  private JLabel actualLowLabel;
  private JLabel actualHighLabel;
  private JLabel actualAverageLabel;
  private JLabel actualFrequencyLabel;
  private JPanel containerPanel;
  private JPanel graphPanel;
  private JLabel upperfaceLabel;
  private JComboBox upperfaceComboBox;
  private UpDownButton upperfaceupdownButton;
  private JLabel lowerfaceLabel;
  private JComboBox lowerfaceComboBox;
  private UpDownButton lowerfaceupdownButton;
  private JLabel eyeLabel;
  private JComboBox eyecomboBox;
  private JRadioButton eyeActive;
  private JCheckBox chckbxNewCheckBox;

  /**
   * Create the application.
   */
  public Express(int width, int height) {
    initialize(width, height);
    setLayout(null);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize(int width, int height) {
    setBounds(0, 0, width, height);

    upperfaceupdownButton = new UpDownButton(70, 0.1, false);
    upperfaceupdownButton.setBounds(135, 30, 70, 30);
    upperfaceupdownButton.setVisible(true);

    lowerfaceupdownButton = new UpDownButton(70, 0.1, false);
    lowerfaceupdownButton.setBounds(370, 30, 70, 30);
    lowerfaceupdownButton.setVisible(true);

    upperfaceComboBox = new JComboBox();
    upperfaceComboBox.setBounds(10, 30, 125, 30);
    upperfaceComboBox.setModel(new DefaultComboBoxModel(new String[] { "Raise Brow", "Furrow Brow" }));

    lowerfaceComboBox = new JComboBox();
    lowerfaceComboBox.setBounds(245, 30, 125, 30);
    lowerfaceComboBox.setModel(new DefaultComboBoxModel(
        new String[] { "Smile", "Clench", "Smirk Left", "Smirk Right", "Laugh" }));

    upperfaceLabel = new JLabel("Upperface:");
    upperfaceLabel.setBounds(10, 10, 100, 20);
    upperfaceLabel.setHorizontalAlignment(SwingConstants.LEFT);

    lowerfaceLabel = new JLabel("Lowerface:");
    lowerfaceLabel.setBounds(245, 10, 100, 20);
    lowerfaceLabel.setHorizontalAlignment(SwingConstants.LEFT);

    eyeLabel = new JLabel("Eye:");
    eyeLabel.setBounds(10, 80, 50, 20);
    eyeLabel.setHorizontalAlignment(SwingConstants.LEFT);

    eyecomboBox = new JComboBox();
    eyecomboBox.setBounds(10, 100, 125, 30);
    eyecomboBox.setModel(new DefaultComboBoxModel(
        new String[] { "Blink", "Wink Left", "Wink Right", "Look Left", "Look Right" }));

    eyeActive = new JRadioButton("Active");
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
}
