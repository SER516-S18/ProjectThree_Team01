package server.gui.panels;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import util.Constants;

public class MenuBarPanel extends JPanel{

  private static final long serialVersionUID = 744880228052339810L;
  private JPanel dropDownPanel;
  private JPanel signalPanel;
  private JPanel middlePanel;
  private JLabel menuLabel;
  ClassLoader cl = getClass().getClassLoader();
  private JLabel signalLabel;
    
  public MenuBarPanel() {
    setBounds (0, 0, 440, 50);
    setLayout(null);
    initialize();
  }
  
  private void initialize() {
    dropDownPanel = new JPanel();
    dropDownPanel.setLayout(null);    
    dropDownPanel.setBounds(0, 0, 50, 50);
    dropDownPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        dropDownPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      }
      
      @Override
      public void mouseExited(MouseEvent e) {
        dropDownPanel.setBorder(null);
      }
    });
    
    menuLabel = new JLabel(new ImageIcon(cl.getResource("menu.png")));
    menuLabel.setBounds(1, 1, 48, 48);
    dropDownPanel.add(menuLabel);
        
    middlePanel = new JPanel();
    middlePanel.setBackground(Constants.WHITE);
    middlePanel.setBounds(50, 0, 340, 50);
    
    signalPanel = new JPanel();
    signalPanel.setLayout(null);
    signalPanel.setBounds(390, 0, 50, 50);    
    signalPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        signalPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
      }

      @Override
      public void mouseExited(MouseEvent e) {
        signalPanel.setBorder(null);
      }
    });
    
    signalLabel = new JLabel(new ImageIcon(cl.getResource("strong.png")));;
    signalLabel.setBounds(1, 1, 48, 48);
    signalPanel.add(signalLabel);
    
    add(dropDownPanel);
    add(middlePanel);
    add(signalPanel);    
  }
}