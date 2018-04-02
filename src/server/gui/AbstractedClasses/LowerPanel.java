package server.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LowerPanel extends JPanel{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JTabbedPane lowerTabbedPane;
  private ContactQualityPanel contactQualityPanel;
  private DetectionPanel detectionPanel;
  
  public LowerPanel(int width, int height) {
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width,int height) {
    setBounds(2, 205, width,height);
    
    lowerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    lowerTabbedPane.setBounds(0, 0, 444, 550);
    
    contactQualityPanel = new ContactQualityPanel();
    detectionPanel = new DetectionPanel(444);
    
    lowerTabbedPane.addTab("Contact Quality", null, contactQualityPanel, null);
    lowerTabbedPane.addTab("Detection", null, detectionPanel, null);
    
    lowerTabbedPane.setSelectedIndex(1);
    
    add(lowerTabbedPane);
    
  }
}