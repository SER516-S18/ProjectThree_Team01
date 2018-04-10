package client.gui.actions;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import client.gui.EmotivControlPanel;

/**
 * This class is responsible for handling Window Events triggered from the
 * server
 * 
 * @author Nikita Bahl
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 09APRIL2018
 *
 */
public class ClientWindowEvents implements WindowListener {
  private JFrame window;

  /*
   * Constructor for this class 
   */
  public ClientWindowEvents(JFrame window) {
    this.window = window;
  }
  
  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
   */
  @Override
  public void windowActivated(WindowEvent arg0) {
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
   */
  @Override
  public void windowClosed(WindowEvent arg0) {
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
   */
  @Override
  public void windowClosing(WindowEvent arg0) {
    if (window instanceof EmotivControlPanel) {
		System.exit(0);
    }
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
   */
  @Override
  public void windowDeactivated(WindowEvent arg0) {
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
   */
  @Override
  public void windowDeiconified(WindowEvent arg0) {
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
   */
  @Override
  public void windowIconified(WindowEvent arg0) {
  }

  /*
   * (non-Javadoc)
   * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
   */
  @Override
  public void windowOpened(WindowEvent arg0) {
  }

}
