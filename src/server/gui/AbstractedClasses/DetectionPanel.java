package server.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetectionPanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private EmoStatePanel emoStatePanel;
  private JLabel lblEmostate;
  private Express upperFace;
  private EmotivLogPanel emotivLogPanel;

  public DetectionPanel(int width) {
    initialize(width);
    setLayout(null);
  }
  
  private void initialize(int width) {
    
    emoStatePanel = new EmoStatePanel(440,175);
    
    lblEmostate = new JLabel("EMOSTATE");
    lblEmostate.setBounds(0, 5, 100, 30);
    
    upperFace = new Express(width, 150);
    upperFace.setBounds(0, 175, 444, 150);
    
    emotivLogPanel = new EmotivLogPanel(444,195);
    
    add(emoStatePanel);
    add(lblEmostate);
    add(upperFace);
    add(emotivLogPanel);
  }
}