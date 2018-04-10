package client.gui.actions;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.gui.panels.*;

/**
 * This class is responsible for handling Mouse Events triggered from the client
 * 
 * @author Nikita Bahl
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 09APRIL2018
 *
 */

public class ClientMouseEvents implements MouseListener {
	  Component actionClass;
	  String switcher;

	  /*
	   * Constructor for this class to get calling component
	   */
	  public ClientMouseEvents(Component actionClass) {
	    this.switcher = "";
	    this.actionClass = actionClass;
	  }

	  /*
	   * Constructor for this class to get calling component and switcher type
	   */
	  public ClientMouseEvents(Component actionClass, String switcher) {
	    this.switcher = switcher;
	    this.actionClass = actionClass;
	  }

	  @Override
	  public void mouseClicked(MouseEvent arg0) {
		  
		  if(actionClass instanceof BoxesPanel && switcher == "V"){
			  ((BoxesPanel) actionClass).createColorChooser();
		  }
		  
		  if(actionClass instanceof ColorChooserPanel){
			  if(switcher == "Color Panel") {
				  ((ColorChooserPanel) actionClass).box.setBoxColor(((JPanel) arg0.getSource()).getBackground());
				  ((ColorChooserPanel) actionClass).setVisible(false);
				  ((ColorChooserPanel) actionClass).dispose();
		      }
			  else if(switcher == "Close") {
				  ((ColorChooserPanel) actionClass).box.setBoxColor( ((ColorChooserPanel) actionClass).colorArray.get( ((ColorChooserPanel) actionClass).colorArray.size() - 1));
				  ((ColorChooserPanel) actionClass).setVisible(false);
				  ((ColorChooserPanel) actionClass).dispose();
		      }
	      }
	  }
	  
	  @Override
	  public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	  }
	  
	  @Override
	  public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	  }
	  
	  @Override
	  public void mousePressed(MouseEvent arg0) {
	    // TODO Auto-generated method stub

	  }

	  @Override
	  public void mouseReleased(MouseEvent arg0) {
	    // TODO Auto-generated method stub

	  }	  
	  
}
