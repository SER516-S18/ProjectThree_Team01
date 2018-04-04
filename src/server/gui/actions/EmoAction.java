package server.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import server.gui.panels.EmoLogPanel;
import server.gui.panels.FacialPanel;
import server.gui.panels.InteractivePanel;

/**
 * This class is responsible for handling Action Events triggered from the
 * server
 * 
 * @author Cephas Armstrong-Mensah
 *
 */
public class EmoAction implements ActionListener {
  private JPanel actionClass;
  private String item;

  public EmoAction(JPanel actionClass) {
    this.actionClass = actionClass;
  }

  public EmoAction(JPanel actionClass, String item) {
    this.actionClass = actionClass;
    this.item = item;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    if (actionClass instanceof InteractivePanel) {
      ((InteractivePanel) actionClass).triggerStartStopSend();
      
    } else if (actionClass instanceof FacialPanel) {
    	if (item == "upperfaceComboBox") {
    	  ((FacialPanel) actionClass).upperfaceAction();
    	}
    	
    	else if (item == "lowerfaceComboBox") {
    	  ((FacialPanel) actionClass).lowerfaceAction();
    	  
       } else if (item == "eyeComboBox") {
    	  ((FacialPanel) actionClass).eyecomboBoxAction(); 
    	  
       }else if(item == "eyeActive") {
    	   ((FacialPanel) actionClass).eyeAction(); 
       }
    	
    } else if (actionClass instanceof EmoLogPanel) {
      ((EmoLogPanel) actionClass).clearConsolePanel();
    }
  }
   
  
  
  /*
} else if (item == "upperfaceComboBox") {
	if (actionClass instanceof FacialPanel) {
		
		((FacialPanel) actionClass)¡£upperfaceAction();
	}
	} else if (item == "lowerfaceComboBox") {
  lowerfaceAction();
} else if (item == "") {
} else if (actionClass instanceof EmoLogPanel) {
  ((EmoLogPanel) actionClass).clearConsolePanel();
}
}
*/
  
  
  
  /*
  public void upperfaceAction() {
    if (actionClass instanceof FacialPanel) {

      String Item = ((FacialPanel) actionClass).getUpperfaceComboBox().getSelectedItem().toString();
      String upperbuttonValue = ((FacialPanel) actionClass).getUpperfaceupdownButton().getOutputText();
      double upperfaceValue = Double.parseDouble(upperbuttonValue);
      if (Item.equals("Raise Brow")) {
        ((FacialPanel) actionClass).setEyebrowRaise(upperfaceValue);

        // System.out.println(upperfaceValue +","+ Item); //test
      }

      if (Item.equals("Furrow Brow")) {
        ((FacialPanel) actionClass).setEyebrowFurrow(upperfaceValue);

        // System.out.println(((FacialPanel) actionClass).getEyebrowFurrow() +","+
        // Item); //test
      }

    }
  }

  public void lowerfaceAction() {
    if (actionClass instanceof FacialPanel) {

      String Item = ((FacialPanel) actionClass).getLowerfaceComboBox().getSelectedItem().toString();
      String lowerbuttonValue = ((FacialPanel) actionClass).getLowerfaceupdownButton().getOutputText();
      Double lowerfaceValue = Double.parseDouble(lowerbuttonValue);

      if (Item.equals("Smile")) {

        ((FacialPanel) actionClass).setSmile(lowerfaceValue);
        // System.out.println(lowerfaceValue +","+ Item); //test
      } else if (Item.equals("Clench")) {

        ((FacialPanel) actionClass).setClench(lowerfaceValue);
        // System.out.println(lowerfaceValue +","+ Item); //test
      } else if (Item.equals("Smirk Left")) {
        ((FacialPanel) actionClass).setLookingUp(lowerfaceValue);
        // System.out.println(lowerfaceValue +","+ Item); //test
      } else if (Item.equals("Smirk Right")) {

        ((FacialPanel) actionClass).setLookingRight(lowerfaceValue);
        // System.out.println(lowerfaceValue +","+ Item); //test
      } else if (Item.equals("Laugh")) {
        ((FacialPanel) actionClass).setLaugh(lowerfaceValue);

        // System.out.println(lowerfaceValue +","+ Item); //test
      } else {
      }
    }
  }
  
 */ 
}
