package server.gui;

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

  
  
  
  //upperface section
  private double eyebrowRaise;
  private double eyebrowFurrow;
  
  //eye section
  private double rightWink;
  private double leftWink;
  private double blink;
  private double lookingRight;
  private double lookingLeft;
 
  //lowerface setion
  private double smrikLeft;
  private double smrikRight;
  private double laugh;	
  private double smile;
  private double clench;

 /*
  *  
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
   */
  private int eyeclickCount = 0;
  private Double eyeActiveValue;

public double getEyebrowRaise() {
	return eyebrowRaise;
}

public double getEyebrowFurrow() {
	return eyebrowFurrow;
}

public double getRightWink() {
	return rightWink;
}

public double getLeftWink() {
	return leftWink;
}

public double getBlink() {
	return blink;
}

public double getLookingRight() {
	return lookingRight;
}

public double getLookingLeft() {
	return lookingLeft;
}

public double getSmrikLeft() {
	return smrikLeft;
}

public double getSmrikRight() {
	return smrikRight;
}

public double getLaugh() {
	return laugh;
}

public double getSmile() {
	return smile;
}

public double getClench() {
	return clench;
}

  
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
    	  eyebrowRaise = upperfaceValue;
        
        //System.out.println(eyebrowRaise +","+ Item);  //test
      }
      
      if(Item.equals("Furrow Brow")) {
    	 eyebrowFurrow = upperfaceValue;
        
        //System.out.println(eyebrowFurrow +","+ Item);   //test
      }
       
        }} );
    
    upperfaceComboBox.setBounds(10, 30, 125, 30);
    upperfaceComboBox.setModel(new DefaultComboBoxModel(new String[] { "Raise Brow", "Furrow Brow" }));

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
        	smrikLeft = lowerfaceValue;
        
         // System.out.println(lookingLeft +","+ Item);   //test
        
        }else if(Item.equals("Smirk Right")){
        	smrikRight = lowerfaceValue;
        
          //System.out.println(lookingRight +","+ Item);   //test
        }else if(Item.equals("Laugh")) {
        	laugh = lowerfaceValue;
        
         // System.out.println(laugh +","+ Item);   //test
        }else {
        
      }
      }
    });
    
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
    
    eyeActive = new JRadioButton("Active");
    //eyeActive.setActionCommand("1");//
  
    /**
     * eye radioButton action
     */
    eyeActive.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		if(eyeclickCount % 2 == 0) {
    			eyeActive.setActionCommand("1");
    		}else {
    			eyeActive.setActionCommand("0");
    		}
    		
    		eyeActiveValue = Double.parseDouble(eyeActive.getActionCommand());
    		
    		eyeclickCount++;
    		System.out.println("eye sectioon:"+ eyeActiveValue);		//test
    	}
    });
    
    eyeActive.setBounds(140, 105, 80, 20);
    
    /**
     * eyecomboBox action 
     */
    eyecomboBox.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {

    		String Item = eyecomboBox.getSelectedItem().toString();
    		eyeActiveValue =  Double.parseDouble(eyeActive.getActionCommand());
    		
			//double eyeActiveValue =  Double.parseDouble(eyeActive.getText());
			System.out.println(eyeActiveValue);			
			
			if(Item.equals("Blink")) {
				blink = eyeActiveValue;
				
				System.out.println(blink +","+ Item);  //test
				
			}else if(Item.equals("Wink Left")) {
				leftWink = eyeActiveValue;
				System.out.println(leftWink +","+ Item);   //test
				
			}else if(Item.equals("Wink Right")){
				rightWink = eyeActiveValue;
				
				System.out.println(rightWink +","+ Item);   //test
				
			}else if(Item.equals("Look Left")){
				lookingLeft = eyeActiveValue;
				
				System.out.println(lookingLeft +","+ Item);   //test
				
			}else if(Item.equals("Look Right")) {
				lookingRight = eyeActiveValue;
				
				System.out.println(lookingRight +","+ Item);   //test
			}else {
				
			}
    	}
    });
    
    
    
    
    
    
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
