package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import server.gui.actions.ActionEvents;
import util.UpDownButton;

/**
 * The purpose of this class is to control the facial section controls and actions for the
 * server's lower panel
 * 
 * @author Zelin Bao
 * @version 1.0
 * @since 02APR2018
 *
 */
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

    upperfaceComboBox.addActionListener(new ActionEvents(this, "upperfaceComboBox"));
    upperfaceComboBox.setBounds(10, 30, 125, 30);
    upperfaceComboBox
        .setModel(new DefaultComboBoxModel<String>(new String[] { "Raise Brow", "Furrow Brow" }));

    lowerfaceComboBox = new JComboBox<String>();

    /**
     * lowerfaceComboBox action
     */

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
    eyeActive.setActionCommand("0");

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
  }

  public void upperfaceAction() {
    String Item = upperfaceComboBox.getSelectedItem().toString();
    String upperbuttonValue = upperfaceupdownButton.getOutputText();
    double upperfaceValue = Double.parseDouble(upperbuttonValue);
    if (Item.equals("Raise Brow")) {
      eyebrowRaise = upperfaceValue;
      // System.out.println(eyebrowRaise +","+ Item); //test
    }

    if (Item.equals("Furrow Brow")) {
      eyebrowFurrow = upperfaceValue;
      // System.out.println(eyebrowFurrow +","+ Item); //test
    }

  }

  public void lowerfaceAction() {
    String Item = lowerfaceComboBox.getSelectedItem().toString();
    String lowerbuttonValue = lowerfaceupdownButton.getOutputText();
    double lowerfaceValue = Double.parseDouble(lowerbuttonValue);
    if (Item.equals("Smile")) {
      smile = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else if (Item.equals("Clench")) {
      clench = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else if (Item.equals("Smirk Left")) {
      lookingUp = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else if (Item.equals("Smirk Right")) {
      lookingDown = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else if (Item.equals("Laugh")) {
      laugh = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else {
    }
  }

  public void eyecomboBoxAction() {
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
      // System.out.println(lookingLeft + "," + Item); // test
    } else if (Item.equals("Look Right")) {
      lookingRight = eyeActiveValue;
      // System.out.println(lookingRight +","+ Item); //test
    } else {
    }
  }

  public void eyeAction() {
    if (eyeclickCount % 2 == 0) {
      eyeActive.setActionCommand("1");
    } else {
      eyeActive.setActionCommand("0");
    }
    eyeActiveValue = Double.parseDouble(eyeActive.getActionCommand());
    eyeclickCount++;
    // System.out.println("eye sectioon:"+ eyeActiveValue); //test
  }
}
