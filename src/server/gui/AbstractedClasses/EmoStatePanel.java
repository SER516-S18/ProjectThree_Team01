package server.gui;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import util.ConsolePanel;
import util.Constants;
import util.UpDownButton;

public class EmoStatePanel extends JPanel{
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JLabel lblTime;
  private JLabel timeTrackerLabel;
  private JLabel secondsLabel;
  private JLabel mentalCommandsLabel;
  private JComboBox neutralComboBox;
  private JComboBox skillComboBox;
  private ConsolePanel consolePanel;
  private UpDownButton neutral;
  private UpDownButton skill;
  private UpDownButton overallSkill;
  private JLabel overallSkillLabel;

  public EmoStatePanel(int width, int height) {
    initialize(width,height);
    setLayout(null);
  }
  
  private void initialize(int width, int height) {
    setBounds(0,0,width,height);
    
    lblTime = new JLabel("Time:");
    lblTime.setBounds(20, 40, 40, 25);
    
    timeTrackerLabel = new JLabel("0.0");
    timeTrackerLabel.setBorder(new LineBorder(Color.DARK_GRAY));
    timeTrackerLabel.setForeground(Color.BLACK);
    timeTrackerLabel.setBackground(Color.DARK_GRAY);
    timeTrackerLabel.setBounds(65, 40, 125, 25);
    
    secondsLabel = new JLabel("Seconds");
    secondsLabel.setBounds(195, 40, 65, 25);
    
    mentalCommandsLabel = new JLabel("Mental Commands");
    mentalCommandsLabel.setBounds(20, 75, 150, 25);
    
    neutralComboBox = new JComboBox();
    neutralComboBox.setBounds(20, 105, 120, 30);
    
    skillComboBox = new JComboBox();
    skillComboBox.setBounds(20, 137, 120, 30);
    
    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 125);
    
    neutral = new UpDownButton(70, 0.1, false);
    neutral.setLocation(140, 105);
    
    skill = new UpDownButton(70, 0.1, false);
    skill.setLocation(140, 137);
    
    overallSkill = new UpDownButton(70, 0.1, false);
    overallSkill.setLocation(370, 105);
    
    overallSkillLabel = new JLabel("Overall Skill");
    overallSkillLabel.setBorder(new LineBorder(Constants.DARKGRAY));
    overallSkillLabel.setBounds(250, 105, 120, 30);
    
    /*public void setTimeTracker(String str) {
      timeTrackerLabel.setText(str);
    }*/
    
    add(lblTime);
    add(timeTrackerLabel);
    add(mentalCommandsLabel);
    add(secondsLabel);
    add(neutralComboBox);
    add(skillComboBox);
    add(overallSkillLabel);
    add(neutral);
    add(skill);
    add(overallSkill);
    
    
  }
}