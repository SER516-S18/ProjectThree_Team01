package server.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.gui.actions.EmoAction;
import util.ConsolePanel;

/**
 * This class's purpose is to display the logging area for the server
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 02APR2018
 *
 */
public class EmoLogPanel extends JPanel {

  private static final long serialVersionUID = 270965232038903697L;
  private static JPanel emoLogPanel;
  private static JLabel labelLog;
  private static ConsolePanel consolePanel;
  private static JButton clearLogButton;

  public EmoLogPanel() {
    setBounds(0, 0, 443, 190);
    setLayout(null);
    initialize();
  }

  private void initialize() {
    labelLog = new JLabel("EMOENGINE LOG");
    labelLog.setBounds(5, 0, 120, 30);
    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 434, 130);
    clearLogButton = new JButton("Clear Log");
    clearLogButton.addActionListener(new EmoAction(this));
    clearLogButton.setBounds(5, 165, 110, 30);

    add(labelLog);
    add(consolePanel);
    add(clearLogButton);
    setVisible(true);
  }

  public void clearConsolePanel() {
    consolePanel.clearText();
  }

  public static ConsolePanel getConsolePanel() {
    return consolePanel;
  }
}
