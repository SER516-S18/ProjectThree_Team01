package server.gui.panels;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import server.gui.EmotivComposer;
import server.gui.actions.EmoMouse;
import util.Constants;

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
    menuLabel.addMouseListener(new EmoMouse(this));
    menuLabel.setBounds(1, 1, 48, 48);
    dropDownPanel.add(menuLabel);

    middlePanel = new JPanel();
    middlePanel.setBackground(Constants.WHITE);
    middlePanel.setBounds(50, 0, 350, 50);

    signalPanel = new JPanel();
    signalPanel.setLayout(null);
    signalPanel.setBounds(400, 0, 50, 50);

    signalLabel = new JLabel(new ImageIcon(loader.getResource("strong.png")));
    signalLabel.addMouseListener(new EmoMouse(this));
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
    EmotivComposer.showMenuItems();
  }

  public void showSignalMenu() {
    EmotivComposer.showSignalItems();
  }

  public static void setIconImage(String filename) {
    System.out.println("Icon image: " + filename);
    signalLabel = new JLabel(new ImageIcon(loader.getResource(filename)));
  }
}