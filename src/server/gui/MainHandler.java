package server.gui;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import server.gui.panels.EmoLogPanel;
import server.gui.panels.EmoStatePanel;
import server.gui.panels.FacialPanel;
import server.gui.panels.HamburgerMenu;
import server.gui.panels.MenuBarPanel;

/**
 * The purpose of this class is to provide the GUI handler for the server and serves as the
 * main interaction between the user, server and client.
 * 
 * @author Cephas Armstrong-Mensah
 *
 */
public class MainHandler {
  private static EmotivComposer frame;

  public static void main(String[] args) {
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
    frame.setVisible(true);
    // createGUIComponents();
  }

  private static void createGUIComponents() {
    // TODO Auto-generated method stub
    HamburgerMenu exitMenu = new HamburgerMenu();
    MenuBarPanel menuBarPanel = new MenuBarPanel();
    JPanel startPanel = new JPanel();
    JPanel emostate = new JPanel();
    FacialPanel emoFacialPanel = new FacialPanel();
    EmoStatePanel emoStatePanel = new EmoStatePanel();
    EmoLogPanel emoLogPanel = new EmoLogPanel();
  }
}
