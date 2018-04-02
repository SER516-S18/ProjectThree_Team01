package server.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DropDownPanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JLabel menuLabel;

  public DropDownPanel(int width, int height) {
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width,int height) {
    setBounds(0, 0, width,height);
    
    menuLabel = new JLabel(new ImageIcon("img/menu.png"));
    menuLabel.setBounds(0, 0, 49, 49);
    
    add(menuLabel);
  }
}