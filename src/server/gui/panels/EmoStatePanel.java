package server.gui.panels;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import server.sys.EmotivRandomizer;
import server.sys.observer.EmotivObserver;
import server.sys.observer.PassedData;
import util.ConsolePanel;
import util.Constants;

/**
 * This class's purpose is the upper section of the lower detection tab area
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 02APR2018
 *
 */
public class EmoStatePanel extends JPanel implements EmotivObserver {

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

  private EmotivRandomizer er;

  public EmoStatePanel(EmotivRandomizer er) {
    setBounds(0, 0, 440, 175);
    setLayout(null);
    this.er = er;
    er.addToObserver(this);

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
    neutralComboBox.setModel(new DefaultComboBoxModel<String>(mentalCommands()));
    neutralComboBox.setEditable(false);
    neutralComboBox.setEnabled(false);
    neutralComboBox.setBounds(20, 105, 120, 30);

    skillComboBox = new JComboBox<String>();
    skillComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Push skill" }));
    skillComboBox.setEditable(false);
    skillComboBox.setEnabled(false);
    skillComboBox.setBounds(20, 137, 120, 30);

    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 125);

    neutral = new ComboControl(er, 70, 0.1, false);
    neutral.setLocation(140, 105);

    skill = new ComboControl(er, 70, 0.1, false);
    skill.setLocation(140, 137);

    overallSkill = new ComboControl(er, 70, 0.1, false);
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

  private String[] mentalCommands() {
    String[] mentalCommandList = new String[14];
    int x = 0;
    mentalCommandList[x++] = "Neutral";
    mentalCommandList[x++] = "Push";
    mentalCommandList[x++] = "Pull";
    mentalCommandList[x++] = "Lift";
    mentalCommandList[x++] = "Drop";
    mentalCommandList[x++] = "Left";
    mentalCommandList[x++] = "Right";
    mentalCommandList[x++] = "Rotate Left";
    mentalCommandList[x++] = "Rotate Right";
    mentalCommandList[x++] = "Rotate Clockwise";
    mentalCommandList[x++] = "Rotate Counterclockwise";
    mentalCommandList[x++] = "Rotate Forward";
    mentalCommandList[x++] = "Rotate Reverse";
    mentalCommandList[x] = "Dissapear";
    return mentalCommandList;
  }

  private void setTimeTrackerLabelText(String str) {
    this.timeTrackerLabel.setText(str);
  }

  @Override
  public void update(PassedData passedData) {
    setTimeTrackerLabelText(String.format("%.2f", passedData.data.getTimer()));
  }
}