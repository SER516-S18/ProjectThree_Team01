package client.gui;

/*
 * This class is used to launch the entire application for the client
 * 
 * @author Nikita Bahl
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 10APRIL2018
 *
 */

import client.gui.EmotivControlPanel;

public class ClientMainClass {
	
    /*
	* Main method to launch the client application
	*/
	public static void main(String[] args) {
	    EmotivControlPanel frame = EmotivControlPanel.getInstance();
	    frame.setVisible(true);
	  }
}
