package server.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmoScriptPanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JLabel label;

  public EmoScriptPanel() {
    initialize();
    setLayout(null);
  }
  
  private void initialize() {
    label=new JLabel("hello");
    label.setBounds(10,10,10,10);
  }
}