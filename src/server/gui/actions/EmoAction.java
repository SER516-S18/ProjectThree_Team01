package server.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import server.gui.panels.FacialPanel;


public class EmoAction implements ActionListener {
	
	JPanel actionClass;
	String item;

	public EmoAction(JPanel actionClass, String item) {
		    this.actionClass = actionClass;
		    this.item = item;
		  }
  
	
	
	
  @Override
  public void actionPerformed(ActionEvent arg0) {
    if(item == "upperfaceComboBox") {
    	upperfaceAction();
    }
   
  
  
  if(item == "lowerfaceComboBox") {
	  lowerfaceAction();
  }
  
  if(item == "") {
	  
  }
  
  
  
  } 
 
  
  public void upperfaceAction() {
	  if (actionClass instanceof FacialPanel) {
			 
			String Item = ((FacialPanel)actionClass).getUpperfaceComboBox().getSelectedItem().toString();
			String upperbuttonValue = ((FacialPanel)actionClass).getUpperfaceupdownButton().getOutputText();
		    double upperfaceValue =  Double.parseDouble(upperbuttonValue);
		    if(Item.equals("Raise Brow")) {
		      ((FacialPanel)actionClass).setEyebrowRaise(upperfaceValue);
		        
		      //System.out.println(upperfaceValue +","+ Item);  //test
		    }
		      
		    if(Item.equals("Furrow Brow")) {
		      ((FacialPanel)actionClass).setEyebrowFurrow(upperfaceValue);
		        
		      //System.out.println(((FacialPanel) actionClass).getEyebrowFurrow() +","+ Item);   //test
		      }
		       
  }
  }
  
  public void lowerfaceAction() {
    if (actionClass instanceof FacialPanel) {
	  
	  String Item = ((FacialPanel)actionClass).getLowerfaceComboBox().getSelectedItem().toString();
	  String lowerbuttonValue = ((FacialPanel)actionClass).getLowerfaceupdownButton().getOutputText();
  	  Double lowerfaceValue = Double.parseDouble(lowerbuttonValue);
	 					
	  if(Item.equals("Smile")) {
			
		((FacialPanel)actionClass).setSmile(lowerfaceValue);			
		 // System.out.println(lowerfaceValue +","+ Item);  //test				
	  }else if(Item.equals("Clench")) {

		  ((FacialPanel)actionClass).setClench(lowerfaceValue);	
		 // System.out.println(lowerfaceValue +","+ Item);   //test			
	  }else if(Item.equals("Smirk Left")){
		  ((FacialPanel)actionClass).setLookingUp(lowerfaceValue);	
		  //System.out.println(lowerfaceValue +","+ Item);   //test			
	  }else if(Item.equals("Smirk Right")){
		  		
		  ((FacialPanel)actionClass).setLookingRight(lowerfaceValue);	
		  //System.out.println(lowerfaceValue +","+ Item);   //test			
	  }else if(Item.equals("Laugh")) {
		  ((FacialPanel)actionClass).setLaugh(lowerfaceValue);	
		 			
		  //System.out.println(lowerfaceValue +","+ Item);   //test
	  }else {				
	  }
    }
 }
  
}

  
  
