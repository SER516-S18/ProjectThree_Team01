package server.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import server.gui.*;
import server.gui.panels.FacialPanel;


public class EmoAction implements ActionListener {
	
	JPanel actionClass;

	public EmoAction(JPanel actionClass) {
		    this.actionClass = actionClass;
		    //this.item = item;
		  }
  
	
	
	
  @Override
  public void actionPerformed(ActionEvent arg0) {

	
	  if (actionClass instanceof FacialPanel) {
		 
	  String Item = ((FacialPanel)actionClass).getUpperfaceComboBox().getSelectedItem().toString();
	  String upperbuttonValue = ((FacialPanel)actionClass).getUpperfaceupdownButton().getOutputText();
      double upperfaceValue =  Double.parseDouble(upperbuttonValue);
      if(Item.equals("Raise Brow")) {
    	
    	  ((FacialPanel)actionClass).setEyebrowRaise(upperfaceValue);
        
        System.out.println(upperfaceValue +","+ Item);  //test
      }
      
      if(Item.equals("Furrow Brow")) {
    	  ((FacialPanel)actionClass).setEyebrowFurrow(upperfaceValue);
        
        System.out.println(((FacialPanel) actionClass).getEyebrowFurrow() +","+ Item);   //test
      }
       
        }} 
  }

