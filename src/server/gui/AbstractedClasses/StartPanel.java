package server.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class StartPanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JTabbedPane tabbedPane;
  private EmoScriptPanel emostate;
  private InteractivePanel interactive;
  
  public StartPanel(int width, int height){
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width, int height) {
    setBounds(2, 50, width, height);
    
    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(0, 0, 444, 120);
    
    
    emostate = new EmoScriptPanel();
    interactive = new InteractivePanel();
    
    tabbedPane.addTab("EMOSCRIPT", null, emostate, null);
    tabbedPane.addTab("INTERACTIVE", null, interactive, null);
  
    this.tabbedPane.setSelectedIndex(1);
    
    add(tabbedPane);
  }
  
}