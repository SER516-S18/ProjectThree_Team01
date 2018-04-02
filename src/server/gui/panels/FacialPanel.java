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

import util.UpDownButton;

public class FacialPanel extends JPanel {

  private static final long serialVersionUID = 3981755002828308262L;

  private JComboBox<String> comboBox;
  private JLabel actualLowLabel;
  private JLabel actualHighLabel;
  private JLabel actualAverageLabel;
  private JLabel actualFrequencyLabel;
  private JPanel containerPanel;
  private JPanel graphPanel;
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

    upperfaceComboBox = new JComboBox();
    
    /**
     * uppercombobox action
     */
    
    upperfaceComboBox.addActionListener(new ActionListener() {
      //private Object upperfaceupdownButton;

    public void actionPerformed(ActionEvent e) {
      
      String Item = upperfaceComboBox.getSelectedItem().toString();
      String upperbuttonValue = upperfaceupdownButton.getOutputText();
      double upperfaceValue =  Double.parseDouble(upperbuttonValue);
      if(Item.equals("Raise Brow")) {
        lookingUp = upperfaceValue;
        
        //System.out.println(upperfaceValue +","+ Item);  //test
      }
      
      if(Item.equals("Furrow Brow")) {
        lookingDown = upperfaceValue;
        
        //System.out.println(upperfaceValue +","+ Item);   //test
      }
       
        }} );
    
    upperfaceComboBox.setBounds(10, 30, 125, 30);
    upperfaceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Raise Brow", "Furrow Brow" }));

    lowerfaceComboBox = new JComboBox();
    
    
    /**
     * lowerfaceComboBox action
     */
    lowerfaceComboBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        
        String Item = lowerfaceComboBox.getSelectedItem().toString();
        String lowerbuttonValue = lowerfaceupdownButton.getOutputText();
      
        double lowerfaceValue =  Double.parseDouble(lowerbuttonValue);
        if(Item.equals("Smile")) {
          smile = lowerfaceValue;
        
          //System.out.println(smile +","+ Item);  //test
        
        }else if(Item.equals("Clench")) {
          clench = lowerfaceValue;
          //System.out.println(clench +","+ Item);   //test
        
        }else if(Item.equals("Smirk Left")){
          lookingLeft = lowerfaceValue;
        
         // System.out.println(lookingLeft +","+ Item);   //test
        
        }else if(Item.equals("Smirk Right")){
          lookingRight = lowerfaceValue;
        
          //System.out.println(lookingRight +","+ Item);   //test
        }else if(Item.equals("Laugh")) {
          laugh = lowerfaceValue;
        
         // System.out.println(laugh +","+ Item);   //test
        }else {
        
      }
      }
    });
    
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

    eyecomboBox = new JComboBox();
    eyecomboBox.setBounds(10, 100, 125, 30);
    eyecomboBox.setModel(new DefaultComboBoxModel<String>(
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
