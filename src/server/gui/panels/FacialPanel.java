package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import server.gui.actions.ActionEvents;

/**
 * The purpose of this class is to control the facial section controls and
 * actions for the server's lower panel
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
  private ComboControl upperfaceupdownButton;
  private JLabel lowerfaceLabel;
  private JComboBox<String> lowerfaceComboBox;
  private ComboControl lowerfaceupdownButton;
  private JLabel eyeLabel;
  private JComboBox<String> eyecomboBox;
  private JRadioButton eyeActive;
  private JCheckBox chckbxNewCheckBox;
  private JButton eyeActiveButton;

  private double eyebrowRaise;
  private double eyebrowFurrow;

  private double smerkRight;
  private double smerkLeft;
  private double smile;
  private double clench;
  private double laugh;

  private int lookingLeft;
  private int lookingRight;
  private int rightWink;
  private int leftWink;
  private int blink;

  private int eyeActiveValue;

  public FacialPanel() {
    setBounds(0, 0, 440, 150);
    initialize();
    setLayout(null);
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    eyebrowRaise = 0;
    eyebrowFurrow = 0;
    smerkRight = 0;
    smerkLeft = 0;
    smile = 0;
    clench = 0;
    laugh = 0;
    lookingLeft = 0;
    lookingRight = 0;
    rightWink = 0;
    leftWink = 0;
    blink = 0;
    eyeActiveValue = 0;
    upperfaceupdownButton = new ComboControl(70, 0.1, false);
    upperfaceupdownButton.setBounds(135, 30, 70, 30);
    upperfaceupdownButton.setVisible(true);

    lowerfaceupdownButton = new ComboControl(70, 0.1, false);
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
      smerkLeft = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else if (Item.equals("Smirk Right")) {
      smerkRight = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else if (Item.equals("Laugh")) {
      laugh = lowerfaceValue;
      // System.out.println(lowerfaceValue +","+ Item); //test
    } else {
    }
  }

  public void eyecomboBoxAction() {
    String Item = eyecomboBox.getSelectedItem().toString();
    // eyeActiveValue = Integer.parseInt(eyeActive.getActionCommand());
    System.out.println(eyeActiveValue);
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

  /*
   * public void eyeAction() { if (eyeclickCount % 2 == 0) {
   * eyeActive.setActionCommand("1");
   * 
   * } else { eyeActive.setActionCommand("0"); } eyeActiveValue =
   * Integer.parseInt(eyeActive.getActionCommand()); eyeclickCount++; //
   * System.out.println("eye sectioon:"+ eyeActiveValue); //test }
   */
  public void eyeAction() {
    if (eyeActive.isSelected()) {
      eyeActiveValue = 1;
    } else {
      eyeActiveValue = 0;
    }
  }
}
