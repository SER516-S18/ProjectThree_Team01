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
import server.sys.EmotivRandomizer;
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
  private EmotivRandomizer er;

  public FacialPanel(EmotivRandomizer er) {
    setBounds(0, 0, 440, 150);
    this.er = er;
    this.er.addToObserver(this);
    isClicked = false;

    setLayout(null);
    initialize();
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
    repaint();
  }

  private void updateLowerFaceAction(String key, double value) {
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

  private void updateUpperFaceAction(String key, double value) {
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

  private void updateEyeAction(String key, int value) {
    EmotivData data = er.getEmotivData();
    // System.out.println(data.getExpressive().toString());
    data.resetExpressiveEyeData();

    // if (!oldSelected.equalsIgnoreCase(key)) {
    System.out.println("Moving forward: ");

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
      } else {
        System.out.println("Not FOUND: " + key + " - " + value);
      }
    }
    // }
    System.out.println(data.getExpressive());

    // oldSelected = key;
    er.notifyObservers();
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

    System.out.println("Eye action: " + eyeActiveValue);

    updateEyeAction(eyecomboBox.getSelectedItem().toString(), eyeActiveValue);
  }

  public void activateButtonAction() {
    System.out.println("Eye active value: " + eyeActiveValue);
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

    if (passedData.isSent) {
      System.out.println("Is sent set: " + passedData.isSent);
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
