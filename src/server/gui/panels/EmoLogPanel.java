package server.gui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ConsolePanel;

public class EmoLogPanel extends JPanel {
  
  private static JPanel emoLogPanel;
  private static JLabel labelLog;
  private static ConsolePanel consolePanel;
  private static JButton clearLogButton;

  public EmoLogPanel() {
    setBounds(0, 0, 440, 210);
    setLayout(null);
    initialize();
  }
  
  private void initialize() {
    labelLog = new JLabel("EMOENGINE LOG");
    labelLog.setBounds(5, 0, 120, 30);
    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 135);
    clearLogButton = new JButton("Clear Log");
    clearLogButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clearConsolePanel();
      }
    });
    clearLogButton.setBounds(5, 170, 110, 30);
        
    add(labelLog);    
    add(consolePanel);
    add(clearLogButton);
    setVisible(true);
  }

  private void clearConsolePanel() {
    consolePanel.clearText();
  }
  
  public static ConsolePanel getConsolePanel() {
    return consolePanel;
  }
}
