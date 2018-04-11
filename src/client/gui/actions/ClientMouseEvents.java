package client.gui.actions;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.plot.XYPlot;

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

	  /*
	   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	   */
	  @Override
	  public void mouseClicked(MouseEvent arg0) {
		  
		  if(actionClass instanceof EmoStatePanel && switcher == "V"){
			  ((EmoStatePanel) actionClass).createColorChooser();
		  }
		  
		  if(actionClass instanceof ColorChooserPanel){
			  if(switcher == "Color Panel") {
				  ((ColorChooserPanel) actionClass).box.setBoxColor(((JPanel) arg0.getSource()).getBackground());
				  XYPlot plot= ((ColorChooserPanel) actionClass).box.parent.performanceGraph.getGraph().getXYPlot();
	              plot.getRenderer().setSeriesPaint(((ColorChooserPanel) actionClass).box.boxNumber, ((JPanel) arg0.getSource()).getBackground());
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
	  
	  /*
	   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	   */
	  @Override
	  public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	  }
	  
	  /*
	   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	   */
	  @Override
	  public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	  }
	  
	  /*
	   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	   */
	  @Override
	  public void mousePressed(MouseEvent arg0) {
	    // TODO Auto-generated method stub

	  }

	  /*
	   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	   */
	  @Override
	  public void mouseReleased(MouseEvent arg0) {
	    // TODO Auto-generated method stub

	  }	    
}
