package server.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import util.Constants;

public class MenuBarPanel extends JPanel{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private DropDownPanel dropDownPanel;
  private SignalPanel signalPanel;
  private JPanel panel;
  
  
  public MenuBarPanel(int width, int height) {
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width,int height) {
    setBounds(0, 0, width,height);
    
    dropDownPanel = new DropDownPanel(50,50);
    dropDownPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
        dropDownPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      }
      
      @Override
      public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
        dropDownPanel.setBorder(null);
      }
    });
    
    signalPanel = new SignalPanel(50,50);
    signalPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
        signalPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
        signalPanel.setBorder(null);
      }
    });
    
    panel = new JPanel();
    panel.setBackground(Constants.WHITE);
    panel.setBounds(52, 0, 341, 50);
    
    add(dropDownPanel);
    add(signalPanel);
    add(panel);
  }
}