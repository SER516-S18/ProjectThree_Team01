package server.gui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * The purpose of this class is to provide the GUI handler for the server and
 * serves as the main interaction between the user, server and client.
 * 
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 03APR2018
 *
 */
public class MainHandler {
  private static EmotivComposer frame;

  /* Constructor for opening the server window */
  public MainHandler() {
    try {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          frame = EmotivComposer.getInstance();
          break;
        }
      }
    } catch (Exception e) {
      frame = EmotivComposer.getInstance();
    }
  }

  /*
   * private static void createGUIComponents() { // TODO Auto-generated method
   * stub HamburgerMenu exitMenu = new HamburgerMenu(frame); MenuBarPanel
   * menuBarPanel = new MenuBarPanel(); JPanel startPanel = new JPanel(); JPanel
   * emostate = new JPanel(); FacialPanel emoFacialPanel = new FacialPanel();
   * EmoStatePanel emoStatePanel = new EmoStatePanel(); InteractivePanel
   * interactivePanel = new InteractivPanel(); LogPanel emoLogPanel = new
   * LogPanel(); }
   */
}
