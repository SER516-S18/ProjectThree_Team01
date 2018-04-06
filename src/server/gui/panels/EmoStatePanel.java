package server.gui.panels;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import util.ConsolePanel;
import util.Constants;

/**
 * This class's purpose is the upper section of the lower detection tab area
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 02APR2018
 *
 */
public class EmoStatePanel extends JPanel {

  private static final long serialVersionUID = -283118474254668985L;
  private JLabel lblTime;
  private JLabel timeTrackerLabel;
  private JLabel secondsLabel;
  private JLabel mentalCommandsLabel;
  private JComboBox<String> neutralComboBox;
  private JComboBox<String> skillComboBox;
  private ConsolePanel consolePanel;
  private ComboControl neutral;
  private ComboControl skill;
  private ComboControl overallSkill;
  private JLabel overallSkillLabel;
  private JLabel emoStateLabel;

  public EmoStatePanel() {
    setBounds(0, 0, 440, 175);
    setLayout(null);
    initialize();
  }

  private void initialize() {
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

    neutralComboBox = new JComboBox<String>();
    neutralComboBox.setBounds(20, 105, 120, 30);

    skillComboBox = new JComboBox<String>();
    skillComboBox.setBounds(20, 137, 120, 30);

    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 125);

    neutral = new ComboControl(70, 0.1, false);
    neutral.setLocation(140, 105);

    skill = new ComboControl(70, 0.1, false);
    skill.setLocation(140, 137);

    overallSkill = new ComboControl(70, 0.1, false);
    overallSkill.setLocation(370, 105);

    overallSkillLabel = new JLabel("Overall Skill");
    overallSkillLabel.setBorder(new LineBorder(Constants.DARKGRAY));
    overallSkillLabel.setBounds(250, 105, 120, 30);

    emoStateLabel = new JLabel("EMOSTATE");
    emoStateLabel.setBounds(5, 5, 100, 30);

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
    add(emoStateLabel);
  }

  public JLabel getTimeTrackerLabel() {
    return this.timeTrackerLabel;
  }

  public void setTimeTrackerLabelText(String str) {
    this.timeTrackerLabel.setText(str);
  }
}