package server.gui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.Constants;

public class SignalPanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JLabel signalLabel;

  public SignalPanel(int width, int height) {
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width,int height) {
    setBounds(394, 0, width,height);
    setBackground(Color.WHITE);
    
    signalLabel = new JLabel(new ImageIcon("img/strong.png"));
    signalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    signalLabel.setForeground(Constants.GREEN);
    signalLabel.setBounds(0, 0, 49, 49);
    
    add(signalLabel);
  }
}