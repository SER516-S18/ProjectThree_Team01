package server.gui.panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import server.gui.EmotivComposer;
import server.gui.actions.MouseEvents;
import util.Constants;

/**
 * This class's purpose is to show a menu area, instead of using JMenuBar I
 * chose to go this route for a better look and feel over the traditional Menu
 * bar.
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 02APR2018
 *
 */
public class MenuBarPanel extends JPanel {

  private static final long serialVersionUID = 744880228052339810L;
  private static JPanel dropDownPanel;
  private static JPanel signalPanel;
  private static JPanel middlePanel;
  private static JLabel menuLabel;
  private static JLabel signalLabel;

  private static ClassLoader loader = MenuBarPanel.class.getClassLoader();

  public MenuBarPanel() {
    setBounds(0, 0, 450, 50);
    setLayout(null);
    initialize();
  }

  private void initialize() {
    dropDownPanel = new JPanel();
    dropDownPanel.setLayout(null);
    dropDownPanel.setBounds(0, 0, 50, 50);

    menuLabel = new JLabel(new ImageIcon(loader.getResource("menu.png")));
    menuLabel.addMouseListener(new MouseEvents(this));
    menuLabel.setBounds(1, 1, 48, 48);
    dropDownPanel.add(menuLabel);

    middlePanel = new JPanel();
    middlePanel.setBackground(Constants.WHITE);
    middlePanel.setBounds(50, 0, 350, 50);

    signalPanel = new JPanel();
    signalPanel.setLayout(null);
    signalPanel.setBounds(400, 0, 50, 50);

    signalLabel = new JLabel(new ImageIcon(loader.getResource("strong.png")));
    signalLabel.addMouseListener(new MouseEvents(this));
    signalLabel.setBounds(1, 1, 48, 48);
    signalPanel.add(signalLabel);

    add(dropDownPanel);
    add(middlePanel);
    add(signalPanel);
  }

  public void showHideBorder(JLabel clickedButton, boolean needsBorder) {
    if (needsBorder) {
      clickedButton.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
    } else {
      clickedButton.setBorder(null);
    }
  }

  public void showExitMenu() {
    HamburgerMenu itemClicked = EmotivComposer.getExitMenu();
    SignalMenu temp = EmotivComposer.getSignalMenu();
    if (temp != null)
      temp.dispose();
    if (itemClicked != null && itemClicked.isVisible()) {
      itemClicked.dispose();
    } else {
      EmotivComposer.showMenuItems();
    }
  }

  public void showSignalMenu() {
    SignalMenu itemClicked = EmotivComposer.getSignalMenu();
    HamburgerMenu temp = EmotivComposer.getExitMenu();
    if (temp != null)
      temp.dispose();
    if (itemClicked != null && itemClicked.isVisible()) {
      itemClicked.dispose();
    } else {
      EmotivComposer.showSignalItems();
    }
  }

  public static void setIconImage(String filename) {
    System.out.println("Icon image: " + filename);
    signalLabel.setIcon(new ImageIcon(loader.getResource(filename)));
  }
}