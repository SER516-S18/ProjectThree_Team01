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
import server.gui.actions.ItemEvents;
import server.sys.SubjectImplementation;
import server.sys.observer.EmotivObserver;
import server.sys.observer.PassedData;

/**
 * The purpose of this class is to control the facial section controls and actions for the
 * server's lower panel
 * 
 * @author Zelin Bao
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 02APR2018
 *
 */
public class FacialPanel extends JPanel implements EmotivObserver {

  private static final long serialVersionUID = 3981755002828308262L;

  private JLabel upperfaceLabel;
  private JLabel lowerfaceLabel;
  private JLabel eyeLabel;
  private JComboBox<String> upperfaceComboBox;
  private JComboBox<String> lowerfaceComboBox;
  private JComboBox<String> eyecomboBox;
  private ComboControl upperfaceupdownButton;
  private ComboControl lowerfaceupdownButton;
  private JRadioButton eyeActive;
  private JCheckBox resetCheckBox;
  private JButton eyeActiveButton;

  private boolean isClicked;

  private int eyeActiveValue;
  private SubjectImplementation er;

  public FacialPanel(SubjectImplementation er) {
    setBounds(0, 0, 440, 150);
    this.er = er;
    this.er.addToObserver(this);
    isClicked = false;

    setLayout(null);
    initialize();
  }

  private void initialize() {
    upperfaceupdownButton = new ComboControl(er, 70, 0.1, false, "Upper Face");
    upperfaceupdownButton.setBounds(135, 30, 70, 30);
    upperfaceupdownButton.setVisible(true);

    lowerfaceupdownButton = new ComboControl(er, 70, 0.1, false, "Lower Face");
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
    eyecomboBox.addActionListener(new ActionEvents(this, "eyeComboBox"));

    eyeActive = new JRadioButton("Active");
    eyeActive.addActionListener(new ActionEvents(this, "eyeActive"));
    eyeActive.setBounds(140, 105, 80, 20);
    eyeActive.setVisible(true);

    eyeActiveButton = new JButton("Activate");
    eyeActiveButton.setBounds(140, 100, 80, 30);
    eyeActiveButton.setVisible(false);
    eyeActiveButton.addActionListener(new ActionEvents(this, "eyeButton"));

    resetCheckBox = new JCheckBox("Auto Reset");
    resetCheckBox.setBounds(270, 105, 115, 20);
    resetCheckBox.addItemListener(new ItemEvents(this));

    add(upperfaceupdownButton);
    add(upperfaceLabel);
    add(lowerfaceLabel);
    add(eyeLabel);
    add(upperfaceComboBox);
    add(eyecomboBox);
    add(lowerfaceComboBox);
    add(lowerfaceupdownButton);
    add(resetCheckBox);
    add(eyeActive);
    add(eyeActiveButton);
  }

  private void replaceRadioButton(boolean isButton) {
    if (isButton) {
      eyeActive.setVisible(false);
      eyeActiveButton.setVisible(true);
    } else {
      eyeActive.setVisible(true);
      eyeActiveButton.setVisible(false);
    }
  }

  private void updateLowerFaceAction(String key, double value) {
    EmotivData data = er.getEmotivData();
    data.resetExpressiveLowerData();

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

    er.updateFacialPanel(false);
  }

  private void updateUpperFaceAction(String key, double value) {
    EmotivData data = er.getEmotivData();
    data.resetExpressiveUpperData();

    if (key.equals("Furrow Brow")) {
      data.setEyebrowFurrow(value);
    } else if (key.equals("Raise Brow")) {
      data.setEyebrowRaise(value);
    }

    er.updateFacialPanel(false);
  }

  private void updateEyeAction(String key, int value) {
    EmotivData data = er.getEmotivData();
    data.resetExpressiveEyeData();

    if (value == 1) {
      if (key.equals("Blink")) {
        data.setBlink(value);
      } else if (key.equals("Look Left")) {
        data.setLookingLeft(value);
      } else if (key.equals("Look Right")) {
        data.setLookingRight(value);
      } else if (key.equals("Wink Right")) {
        data.setRightWink(value);
      } else if (key.equals("Wink Left")) {
        data.setLeftWink(value);
      }
    }

    er.notifyObservers();
  }

  /**
   * This method controls the upper face action area
   */
  public void upperfaceAction() {
    String item = upperfaceComboBox.getSelectedItem().toString();
    String upperbuttonValue = upperfaceupdownButton.getOutputText();
    double upperfaceValue = Double.parseDouble(upperbuttonValue);
    updateUpperFaceAction(item, upperfaceValue);
  }

  /**
   * This method controls the lower face action area
   */
  public void lowerfaceAction() {
    String item = lowerfaceComboBox.getSelectedItem().toString();
    String lowerbuttonValue = lowerfaceupdownButton.getOutputText();
    double lowerfaceValue = Double.parseDouble(lowerbuttonValue);
    updateLowerFaceAction(item, lowerfaceValue);
  }

  /**
   * This method controls the selected item for the Eye area combo item
   */
  public void eyecomboBoxAction() {
    String item = eyecomboBox.getSelectedItem().toString();
    updateEyeAction(item, eyeActiveValue);
  }

  /**
   * This method controls the radio button value for Eye Action
   */
  public void eyeAction() {
    if (eyeActive.isSelected()) {
      eyeActiveValue = 1;
    } else {
      eyeActiveValue = 0;
    }

    updateEyeAction(eyecomboBox.getSelectedItem().toString(), eyeActiveValue);
  }

  /**
   * This method controls the action performed when the Activate button is shown and clicked
   */
  public void activateButtonAction() {
    if (eyeActiveValue == 0) {
      eyeActiveValue = 1;
    } else {
      eyeActiveValue = 0;
    }

    isClicked = !isClicked;
    updateEyeAction(eyecomboBox.getSelectedItem().toString(), eyeActiveValue);
  }

  @Override
  public void update(PassedData passedData) {
    replaceRadioButton(passedData.interactiveAutoReset);
    if (passedData.facial) {
      lowerfaceAction();
      upperfaceAction();
    }

    if (passedData.isSent) {
      if (resetCheckBox.isSelected()) {
        System.out.println("Reset");
        if (eyeActive.isSelected()) {
          eyeActive.doClick();
        } else if (isClicked) {
          activateButtonAction();
        }
      }
    }
  }
}
