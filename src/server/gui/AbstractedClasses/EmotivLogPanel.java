package server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.ConsolePanel;

public class EmotivLogPanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JLabel lblNewLabel;
  private JButton clearLogButton;
  private ConsolePanel consolePanel;
  
  public EmotivLogPanel(int width,int height) {
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width,int height) {
    setBounds(0,325,width,height);
    
    lblNewLabel = new JLabel("EMOENGINE LOG");
    lblNewLabel.setBounds(5, 0, 120, 30);
    
    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 125);
    
    clearLogButton = new JButton("Clear Log");
    clearLogButton.setBounds(5, 160, 110, 30);
  /* clearLogButton.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {

        clearConsolePanel();

      }

    })*/

    clearLogButton.setBounds(5, 160, 110, 30);
    
    add(lblNewLabel);
    add(consolePanel);
    add(clearLogButton);
  }

  /*protected void clearConsolePanel() {
    consolePanel.clearText();
    
  }*/
}