package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import data.EmotivData;
import server.gui.actions.ActionEvents;
import server.sys.EmotivRandomizer;
import server.sys.observer.EmotivObserver;
import server.sys.observer.PassedData;

/**
 * The purpose of this class is to control the facial section controls and
 * actions for the server's lower panel
 * 
 * @author Zelin Bao
 * @version 1.0
 * @since 02APR2018
 *
 */
public class FacialPanel extends JPanel implements EmotivObserver {

  private static final long serialVersionUID = 3981755002828308262L;

  private JLabel upperfaceLabel;
  private JComboBox<String> upperfaceComboBox;
  private ComboControl upperfaceupdownButton;
  private JLabel lowerfaceLabel;
  private JComboBox<String> lowerfaceComboBox;
  private ComboControl lowerfaceupdownButton;
  private JLabel eyeLabel;
  private JComboBox<String> eyecomboBox;
  private JRadioButton eyeActive;
  private JCheckBox chckbxNewCheckBox;
  private JButton eyeActiveButton;

  private int eyeActiveValue;
  private EmotivRandomizer er;

  public FacialPanel(EmotivRandomizer er) {
    setBounds(0, 0, 440, 150);
    this.er = er;
    initialize();

    setLayout(null);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    upperfaceupdownButton = new ComboControl(er, 70, 0.1, false);
    upperfaceupdownButton.setBounds(135, 30, 70, 30);
    upperfaceupdownButton.setVisible(true);

    lowerfaceupdownButton = new ComboControl(er, 70, 0.1, false);
    lowerfaceupdownButton.setBounds(365, 30, 70, 30);
    lowerfaceupdownButton.setVisible(true);

    upperfaceComboBox = new JComboBox<String>();
    upperfaceComboBox.addActionListener(new ActionEvents(this, "upperfaceComboBox"));
    upperfaceComboBox.setBounds(10, 30, 125, 30);
    upperfaceComboBox
        .setModel(new DefaultComboBoxModel<String>(new String[] { "Raise Brow", "Furrow Brow" }));

    lowerfaceComboBox = new JComboBox<String>();
    lowerfaceComboBox.addActionListener(new ActionEvents(this, "lowerfaceComboBox"));
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

    eyeActive.addActionListener(new ActionEvents(this, "eyeActive"));
    eyecomboBox.addActionListener(new ActionEvents(this, "eyeComboBox"));
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

    updateEyeAction(eyecomboBox.getSelectedItem().toString(), 1);
    updateLowerFaceAction(upperfaceComboBox.getSelectedItem().toString(),
        Double.parseDouble(upperfaceupdownButton.getOutputText()));
    updateUpperFaceAction(lowerfaceComboBox.getSelectedItem().toString(),
        Double.parseDouble(lowerfaceupdownButton.getOutputText()));
  }

  public void replaceRadioButton(boolean isButton) {
    if (isButton) {
      remove(eyeActive);
      eyeActiveButton = new JButton("Activate");
      eyeActiveButton.setBounds(140, 100, 80, 30);
      eyeActiveButton.setVisible(true);
      add(eyeActiveButton);
    } else {
      remove(eyeActiveButton);
      add(eyeActive);
    }
    repaint();
  }

  public JRadioButton getEyeActive() {
    return eyeActive;
  }

  public JCheckBox getEyeAutoCheckBox() {
    return chckbxNewCheckBox;
  }

  public void seteyeActiveValue(int number) {
    eyeActiveValue = number;
  }

  public boolean ischckbxNewCheckBoxSelected() {
    return chckbxNewCheckBox.isSelected();
  }

  public void upperfaceAction() {
    String item = upperfaceComboBox.getSelectedItem().toString();
    String upperbuttonValue = upperfaceupdownButton.getOutputText();
    double upperfaceValue = Double.parseDouble(upperbuttonValue);
    updateUpperFaceAction(item, upperfaceValue);
  }

  public void lowerfaceAction() {
    String item = lowerfaceComboBox.getSelectedItem().toString();
    String lowerbuttonValue = lowerfaceupdownButton.getOutputText();
    double lowerfaceValue = Double.parseDouble(lowerbuttonValue);
    updateLowerFaceAction(item, lowerfaceValue);
  }

  public void eyecomboBoxAction() {
    String item = eyecomboBox.getSelectedItem().toString();
    updateEyeAction(item, eyeActiveValue);
  }

  public void eyeAction() {
    if (eyeActive.isSelected()) {
      eyeActiveValue = 1;
    } else {
      eyeActiveValue = 0;
    }
  }

  public void updateLowerFaceAction(String key, double value) {
    EmotivData data = er.getEmotivData();
    data.resetExpressiveLowerData();

    System.out.println("Lowerface: " + key + " - " + value);

    if (key.equals("Smirk Right")) {
      data.setSmirkRight(value);
    } else if (key.equals("Smirk Left")) {
      data.setSmirkLeft(value);
    } else if (key.equals("Smile")) {
      data.setSmile(value);
    } else if (key.equals("Clench")) {
      data.setClench(value);
    } else if (key.equals("Laugh")) {
      data.setLaugh(value);
    } else {
      System.out.println("Not FOUND: " + key + " - " + value);
    }

    er.notifyObservers();
  }

  public void updateUpperFaceAction(String key, double value) {
    EmotivData data = er.getEmotivData();
    data.resetExpressiveUpperData();
    System.out.println("Upperface: " + key + " - " + value);

    if (key.equals("Furrow Brow")) {
      data.setEyebrowFurrow(value);
    } else if (key.equals("Raise Brow")) {
      data.setEyebrowRaise(value);
    } else {
      System.out.println("Not FOUND: " + key + " - " + value);
    }

    er.notifyObservers();
  }

  public void updateEyeAction(String key, int value) {
    EmotivData data = er.getEmotivData();
    data.resetExpressiveEyeData();

    System.out.println("Eye Action: " + key + " - " + value);

    if (key.equals("Blink")) {
      data.setBlink(value);
    } else if (key.equals("Look Left")) {
      data.setLookingLeft(value);
    } else if (key.equals("Look Right")) {
      data.setLookingRight(value);
    } else if (key.equals("Wink Right")) {
      data.setLeftWink(value);
    } else if (key.equals("Wink Left")) {
      data.setRightWink(0);
    } else {
      System.out.println("Not FOUND: " + key + " - " + value);
    }

    er.notifyObservers();
  }

  @Override
  public void update(PassedData passedData) {
  }
}
