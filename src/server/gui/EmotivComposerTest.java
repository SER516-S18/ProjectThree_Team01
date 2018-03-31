package server.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.websocket.DeploymentException;

import server.sys.ServerWebSocket;
import util.ConsolePanel;
import util.Constants;
import util.UpDownButton;

public class EmotivComposerTest extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private JPanel contentPane;
  private ConsolePanel consolePanel;
  private JTabbedPane tabbedPane;
  private JPanel startPanel;
  private JPanel interactive;
  private JLabel playerLabel;
  private JLabel emoStateLabel;
  private JLabel secLabel;
  private JButton sendButton;
  private JCheckBox autoResetCheckBox;
  private JComboBox playerComboBox;
  private JPanel emostate;
  private JPanel detectionPanel;
  private UpDownButton incrementDecrement;
  private JLabel menuLabel;
  private JLabel dropDownLabel;
  private JPanel dropDownPanel;
  private JPanel signalPanel;
  private JLabel signalLabel;
  private boolean isAutoResetChecked = false;
  private JLabel emoStatLabel;
  private JLabel timeLabel;
  private JTextField enterTime;
  private JLabel secondsLabel;
  private JLabel mentalCommandLabel;
  private JComboBox mentalCommandComboBox1;
  private JComboBox mentalCommandComboBox2;
  private JLabel overallSkillLabel;
  private JLabel upperFaceLabel;
  private JComboBox upperFaceComboBox;
  private JLabel lowerFaceLabel;
  private JComboBox lowerFaceComboBox;
  private JLabel eyeLabel;
  private JComboBox eyeComboBox;
  private JLabel trainingResultLabel;
  private JLabel mentalCommandsLabel;
  private JComboBox mentalCommandsComboBox;
  private JLabel performanceMetricsLabel;
  private JComboBox performanceMetricsComboBox;
  private UpDownButton incrementDecrementMentalCommand1;
  private UpDownButton incrementDecrementMentalCommand2;
  private UpDownButton incrementDecrementOverallSkill;
  private UpDownButton incrementDecrementUpperFace;
  private UpDownButton incrementDecrementLowerFace;
  private UpDownButton incrementDecrementEye;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          EmotivComposerTest frame = new EmotivComposerTest();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public EmotivComposerTest() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 800);

    contentPane = new JPanel();
    contentPane.setBackground(Constants.PEACH);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    startPanel = new JPanel();
    startPanel.setBounds(2, 50, 444, 125);
    contentPane.add(startPanel);
    startPanel.setLayout(null);

    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane.setBounds(0, 0, 444, 120);
    startPanel.add(tabbedPane);

    interactive = new JPanel();
    tabbedPane.addTab("INTERACTIVE", null, interactive, null);
    interactive.setLayout(null);

    playerLabel = new JLabel("Player");
    playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    playerLabel.setBounds(10, 10, 60, 15);
    interactive.add(playerLabel);

    emoStateLabel = new JLabel("<html>EmoState<br>Interval:</html>");
    emoStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emoStateLabel.setBounds(180, 10, 60, 30);
    interactive.add(emoStateLabel);

    secLabel = new JLabel("Sec");
    secLabel.setBounds(370, 15, 30, 15);
    interactive.add(secLabel);
    JPanel menuBarPanel = new JPanel();
    menuBarPanel.setBounds(0, 0, 444, 50);
    contentPane.add(menuBarPanel);
    menuBarPanel.setLayout(null);

    dropDownPanel = new JPanel();
    dropDownPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    dropDownPanel.setBounds(0, 0, 50, 50);
    menuBarPanel.add(dropDownPanel);
    dropDownPanel.setLayout(null);

    menuLabel = new JLabel(new ImageIcon("./img/menu.png"));
    menuLabel.setBounds(0, 0, 50, 50);
    dropDownPanel.add(menuLabel);

    signalPanel = new JPanel();
    signalPanel.setBounds(394, 0, 50, 50);
    signalPanel.setBackground(Color.WHITE);
    menuBarPanel.add(signalPanel);
    signalPanel.setLayout(null);

    JLabel signalLabel = new JLabel(Character.toString((char) 0x2022), SwingConstants.CENTER);
    signalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    signalLabel.setForeground(Constants.GREEN);
    signalLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 100));
    signalLabel.setBounds(0, 0, 50, 50);
    signalPanel.add(signalLabel);

    signalLabel.setForeground(Color.GREEN);
    signalLabel.setFont(new Font("DejaVu Sans", Font.PLAIN, 85));
    signalLabel.setBounds(0, 0, 50, 50);
    signalPanel.add(signalLabel);

    sendButton = new JButton("Send");
    sendButton.setBounds(330, 55, 100, 30);
    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleStartStopSend();
      }
    });
    interactive.add(sendButton);

    autoResetCheckBox = new JCheckBox("Auto Reset");
    autoResetCheckBox.setBounds(170, 55, 115, 18);
    autoResetCheckBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        System.out.println("Checked: " + autoResetCheckBox.isSelected());
        if (autoResetCheckBox.isSelected()) {
          sendButton.setText("Start");
          autoResetCheckBox.setEnabled(false);
          isAutoResetChecked = true;
        } else {
          sendButton.setText("Send");
          isAutoResetChecked = false;
        }
      }
    });
    interactive.add(autoResetCheckBox);

    playerComboBox = new JComboBox();
    playerComboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1", "2" }));
    playerComboBox.setBounds(65, 10, 80, 30);
    interactive.add(playerComboBox);

    incrementDecrement = new UpDownButton(110, 0.5, true);
    incrementDecrement.setLocation(250, 10);
    interactive.add(incrementDecrement);

    emostate = new JPanel();
    tabbedPane.addTab("EMOSCRIPT", null, emostate, null);
    emostate.setLayout(null);

    detectionPanel = new JPanel();
    detectionPanel.setBounds(2, 200, 444, 538);
    detectionPanel.setLayout(null);
    contentPane.add(detectionPanel);

    emoStatLabel = new JLabel("EMOSTAT");
    emoStatLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    emoStatLabel.setHorizontalAlignment(SwingConstants.LEFT);
    emoStatLabel.setBounds(10, 10, 60, 15);

    timeLabel = new JLabel("Time: ");
    timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
    timeLabel.setBounds(10, 40, 60, 15);

    enterTime = new JTextField("0");
    enterTime.setBounds(70, 35, 100, 25);

    secondsLabel = new JLabel("Seconds ");
    secondsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    secondsLabel.setHorizontalAlignment(SwingConstants.LEFT);
    secondsLabel.setBounds(180, 40, 60, 15);

    mentalCommandLabel = new JLabel("Mental Command ");
    mentalCommandLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    mentalCommandLabel.setHorizontalAlignment(SwingConstants.LEFT);
    mentalCommandLabel.setBounds(10, 70, 150, 15);

    mentalCommandComboBox1 = new JComboBox();
    mentalCommandComboBox1.setModel(new DefaultComboBoxModel(new String[] { "Neutral", "Push", "Pull",
        "Lift", "Drop", "Left", "Right", "Rotate Left", "Rotate Right", "Rotate Clockwise",
        "Rotate Counter-clockwise", "Rotate Forward", "Rotate Reverse", "Disappear" }));
    mentalCommandComboBox1.setBounds(10, 90, 170, 30);

    mentalCommandComboBox2 = new JComboBox();
    mentalCommandComboBox2.setModel(new DefaultComboBoxModel(new String[] { "Push Skill" }));
    mentalCommandComboBox2.setBounds(10, 121, 170, 30);

    overallSkillLabel = new JLabel("Overall Skill");
    overallSkillLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    overallSkillLabel.setHorizontalAlignment(SwingConstants.LEFT);
    overallSkillLabel.setBounds(10, 152, 150, 30);

    upperFaceLabel = new JLabel("Upper Face ");
    upperFaceLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    upperFaceLabel.setHorizontalAlignment(SwingConstants.LEFT);
    upperFaceLabel.setBounds(10, 181, 150, 15);

    upperFaceComboBox = new JComboBox();
    upperFaceComboBox.setModel(new DefaultComboBoxModel(new String[] { "Raise Brow", "Furrow Brow" }));
    upperFaceComboBox.setBounds(10, 201, 170, 30);

    lowerFaceLabel = new JLabel("Lower Face ");
    lowerFaceLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    lowerFaceLabel.setHorizontalAlignment(SwingConstants.LEFT);
    lowerFaceLabel.setBounds(10, 246, 150, 15);

    lowerFaceComboBox = new JComboBox();
    lowerFaceComboBox.setModel(new DefaultComboBoxModel(
        new String[] { "Smile", "Clench", "Smirk Left", "Smirk Right", "Laugh" }));
    lowerFaceComboBox.setBounds(10, 262, 170, 30);

    eyeLabel = new JLabel("Eye ");
    eyeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    eyeLabel.setHorizontalAlignment(SwingConstants.LEFT);
    eyeLabel.setBounds(10, 307, 150, 15);

    eyeComboBox = new JComboBox();
    eyeComboBox.setModel(new DefaultComboBoxModel(
        new String[] { "Blink", "Wink Left", "Wink Right", "Look Left", "Look Right" }));
    eyeComboBox.setBounds(10, 327, 170, 30);

    trainingResultLabel = new JLabel("TRAINING RESULT ");
    trainingResultLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    trainingResultLabel.setHorizontalAlignment(SwingConstants.LEFT);
    trainingResultLabel.setBounds(10, 372, 150, 15);

    mentalCommandsLabel = new JLabel("Mental Commands ");
    mentalCommandsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    mentalCommandsLabel.setHorizontalAlignment(SwingConstants.LEFT);
    mentalCommandsLabel.setBounds(10, 402, 150, 15);

    mentalCommandsComboBox = new JComboBox();
    mentalCommandsComboBox.setModel(new DefaultComboBoxModel(new String[] { "OK", "Not enough data" }));
    mentalCommandsComboBox.setBounds(10, 422, 170, 30);

    performanceMetricsLabel = new JLabel("Performance Metrics ");
    performanceMetricsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    performanceMetricsLabel.setHorizontalAlignment(SwingConstants.LEFT);
    performanceMetricsLabel.setBounds(10, 467, 150, 15);

    performanceMetricsComboBox = new JComboBox();
    performanceMetricsComboBox
        .setModel(new DefaultComboBoxModel(new String[] { "OK", "Not enough data" }));
    performanceMetricsComboBox.setBounds(10, 487, 170, 30);

    incrementDecrementMentalCommand1 = new UpDownButton(110, 0.5, true);
    incrementDecrementMentalCommand1.setLocation(181, 90);
    detectionPanel.add(incrementDecrementMentalCommand1);

    incrementDecrementMentalCommand2 = new UpDownButton(110, 0.5, true);
    incrementDecrementMentalCommand2.setLocation(181, 121);
    detectionPanel.add(incrementDecrementMentalCommand2);

    incrementDecrementOverallSkill = new UpDownButton(110, 0.5, true);
    incrementDecrementOverallSkill.setLocation(181, 152);
    detectionPanel.add(incrementDecrementOverallSkill);

    incrementDecrementUpperFace = new UpDownButton(110, 0.5, true);
    incrementDecrementUpperFace.setLocation(181, 200);
    detectionPanel.add(incrementDecrementUpperFace);

    incrementDecrementLowerFace = new UpDownButton(110, 0.5, true);
    incrementDecrementLowerFace.setLocation(181, 262);
    detectionPanel.add(incrementDecrementLowerFace);

    incrementDecrementEye = new UpDownButton(110, 0.5, true);
    incrementDecrementEye.setLocation(181, 327);
    detectionPanel.add(incrementDecrementEye);

    detectionPanel.add(emoStatLabel);
    detectionPanel.add(timeLabel);
    detectionPanel.add(enterTime);
    detectionPanel.add(secondsLabel);
    detectionPanel.add(mentalCommandLabel);
    detectionPanel.add(mentalCommandComboBox1);
    detectionPanel.add(mentalCommandComboBox2);
    detectionPanel.add(overallSkillLabel);
    detectionPanel.add(upperFaceLabel);
    detectionPanel.add(upperFaceComboBox);
    detectionPanel.add(lowerFaceLabel);
    detectionPanel.add(lowerFaceComboBox);
    detectionPanel.add(eyeLabel);
    detectionPanel.add(eyeComboBox);
    detectionPanel.add(trainingResultLabel);
    detectionPanel.add(mentalCommandsLabel);
    detectionPanel.add(mentalCommandsComboBox);
    detectionPanel.add(performanceMetricsLabel);
    detectionPanel.add(performanceMetricsComboBox);

    // startServer();
  }

  private void startServer() {
    org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost",
        Constants.PORT, Constants.LINK, ServerWebSocket.class);
    try {
      server.start();
      System.out.println("Server started successfully...");
    } catch (DeploymentException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Updating the Console to output status message
   * 
   * @param message
   */
  private void updateConsolePanel(String message) {
    consolePanel.updateText(message + "&emsp;&emsp&lt;" + LocalTime.now() + "&gt;");
  }

  private void handleStartStopSend() {
    if (isAutoResetChecked) {
      if (sendButton.getText().equalsIgnoreCase("Start")) {
        // Send based on frequency
        sendButton.setText("Stop");
        autoResetCheckBox.setEnabled(false);
      } else {
        // Close websocket
        sendButton.setText("Start");
        autoResetCheckBox.setEnabled(true);
      }
    } else {
      // Send one message
    }
  }
}
