package server.gui.actions;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import server.gui.EmotivComposer;

/**
 * This class is responsible for handling Window Events triggered from the server
 * 
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 04APR2018
 *
 */
public class WindowEvents implements WindowListener {
  private JFrame window;

  public WindowEvents(JFrame window) {
    this.window = window;
  }

  @Override
  public void windowActivated(WindowEvent arg0) {
  }

  @Override
  public void windowClosed(WindowEvent arg0) {
  }

  @Override
  public void windowClosing(WindowEvent arg0) {
    if (window instanceof EmotivComposer) {
      ((EmotivComposer) window).closeThread();
    }
  }

  @Override
  public void windowDeactivated(WindowEvent arg0) {
  }

  @Override
  public void windowDeiconified(WindowEvent arg0) {
  }

  @Override
  public void windowIconified(WindowEvent arg0) {
  }

  @Override
  public void windowOpened(WindowEvent arg0) {
  }

}
