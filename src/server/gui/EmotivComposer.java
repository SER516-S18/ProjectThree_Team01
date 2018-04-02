package server.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.server.Server;

import server.sys.ServerThread;
import server.sys.ServerWebSocket;
import server.sys.WorkerThread;
import util.ConsolePanel;
import util.Constants;
import util.UpDownButton;

public class EmotivComposer extends JFrame implements WindowListener {

  private static final long serialVersionUID = 6196061116172281774L;
  private static WorkerThread worker;
  private static Thread workerThread;

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
  private JComboBox<String> playerComboBox;
  private JPanel emostate;
  private JPanel lowerPanel;
  private UpDownButton incrementDecrement;
  private JLabel menuLabel;
  private JLabel dropDownLabel;
  private JPanel dropDownPanel;
  private JPanel signalPanel;
  private JLabel signalLabel;
  private JTabbedPane lowerTabbedPane;
  private JPanel contactQualityPanel;
  private JPanel detectionPanel;
  private Express upperFace;

  private boolean isAutoResetChecked = false;
  private JPanel panel;

  private static EmotivComposer instance = null;
  private JPanel emoStatePanel;
  private JLabel lblEmostate;
  private JLabel lblTime;
  private JLabel timeTrackerLabel;
  private JLabel secondsLabel;
  private JButton clearLogButton;
  private JLabel lblNewLabel;
  private JPanel emotivLogPanel;
  private JLabel mentalCommandsLabel;
  private JComboBox skillComboBox;
  private JComboBox neutralComboBox;
  private UpDownButton neutral;
  private UpDownButton skill;
  private UpDownButton overallSkill;
  private JLabel overallSkillLabel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EmotivComposer frame = EmotivComposer.getInstance();
    frame.setVisible(true);
  }

  public static EmotivComposer getInstance() {
    if (instance == null) {
      instance = new EmotivComposer();
    }
    return instance;
  };

  /**
   * Create the frame.
   */
  private EmotivComposer() {
    addWindowListener(this);
    setTitle("Emotiv Composer Project 3");
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

    emostate = new JPanel();
    tabbedPane.addTab("EMOSCRIPT", null, emostate, null);
    emostate.setLayout(null);

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
    dropDownPanel.setBounds(0, 0, 50, 50);
    menuBarPanel.add(dropDownPanel);
    dropDownPanel.setLayout(null);

    menuLabel = new JLabel(new ImageIcon("img/menu.png"));
    menuLabel.setBounds(0, 0, 49, 49);
    dropDownPanel.add(menuLabel);

    signalPanel = new JPanel();
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
    signalPanel.setBounds(394, 0, 50, 50);
    signalPanel.setBackground(Color.WHITE);
    menuBarPanel.add(signalPanel);
    signalPanel.setLayout(null);

    signalLabel = new JLabel(new ImageIcon("img/strong.png"));
    signalLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    signalLabel.setForeground(Constants.GREEN);
    signalLabel.setBounds(0, 0, 49, 49);
    signalPanel.add(signalLabel);

    panel = new JPanel();
    panel.setBackground(Constants.WHITE);
    panel.setBounds(52, 0, 341, 50);
    menuBarPanel.add(panel);

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

    playerComboBox = new JComboBox<String>();
    playerComboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1" }));
    playerComboBox.setBounds(65, 10, 80, 30);
    interactive.add(playerComboBox);

    incrementDecrement = new UpDownButton(110, 0.5, true);
    incrementDecrement.setLocation(250, 10);
    incrementDecrement.setOutputText("0.25");
    interactive.add(incrementDecrement);

    lowerPanel = new JPanel();
    lowerPanel.setBounds(2, 205, 444, 550);
    contentPane.add(lowerPanel);
    lowerPanel.setLayout(null);

    lowerTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    lowerTabbedPane.setBounds(0, 0, 444, 550);
    lowerPanel.add(lowerTabbedPane);

    contactQualityPanel = new JPanel();
    lowerTabbedPane.addTab("Contact Quality", null, contactQualityPanel, null);
    contactQualityPanel.setLayout(null);

    detectionPanel = new JPanel();
    detectionPanel.setLayout(null);

    emoStatePanel = new JPanel();
    emoStatePanel.setBounds(0, 0, 440, 175);
    detectionPanel.add(emoStatePanel);
    emoStatePanel.setLayout(null);

    lblEmostate = new JLabel("EMOSTATE");
    lblEmostate.setBounds(0, 5, 100, 30);
    emoStatePanel.add(lblEmostate);

    lblTime = new JLabel("Time:");
    lblTime.setBounds(20, 40, 40, 25);
    emoStatePanel.add(lblTime);

    timeTrackerLabel = new JLabel("0.0");
    timeTrackerLabel.setBorder(new LineBorder(Color.DARK_GRAY));
    timeTrackerLabel.setForeground(Color.BLACK);
    timeTrackerLabel.setBackground(Color.DARK_GRAY);
    timeTrackerLabel.setBounds(65, 40, 125, 25);
    emoStatePanel.add(timeTrackerLabel);

    secondsLabel = new JLabel("Seconds");
    secondsLabel.setBounds(195, 40, 65, 25);
    emoStatePanel.add(secondsLabel);

    mentalCommandsLabel = new JLabel("Mental Commands");
    mentalCommandsLabel.setBounds(20, 75, 150, 25);
    emoStatePanel.add(mentalCommandsLabel);

    neutralComboBox = new JComboBox();
    neutralComboBox.setBounds(20, 105, 120, 30);
    emoStatePanel.add(neutralComboBox);

    skillComboBox = new JComboBox();
    skillComboBox.setBounds(20, 137, 120, 30);
    emoStatePanel.add(skillComboBox);
    upperFace = new Express(detectionPanel.getWidth(), 150);
    upperFace.setBounds(0, 175, 444, 150);
    detectionPanel.add(upperFace);

    lowerTabbedPane.addTab("Detection", null, detectionPanel, null);

    emotivLogPanel = new JPanel();
    emotivLogPanel.setBounds(0, 325, 444, 195);
    emotivLogPanel.setLayout(null);

    lblNewLabel = new JLabel("EMOENGINE LOG");
    lblNewLabel.setBounds(5, 0, 120, 30);
    emotivLogPanel.add(lblNewLabel);

    consolePanel = new ConsolePanel();
    consolePanel.setBounds(5, 30, 430, 125);
    emotivLogPanel.add(consolePanel);

    detectionPanel.add(emotivLogPanel);

    clearLogButton = new JButton("Clear Log");
    clearLogButton.setBounds(5, 160, 110, 30);
    emotivLogPanel.add(clearLogButton);

    neutral = new UpDownButton(70, 0.1, false);
    neutral.setLocation(140, 105);
    skill = new UpDownButton(70, 0.1, false);
    skill.setLocation(140, 137);
    overallSkill = new UpDownButton(70, 0.1, false);
    overallSkill.setLocation(370, 105);

    emoStatePanel.add(neutral);
    emoStatePanel.add(skill);
    emoStatePanel.add(overallSkill);

    overallSkillLabel = new JLabel("Overall Skill");
    overallSkillLabel.setBorder(new LineBorder(Constants.DARKGRAY));
    overallSkillLabel.setBounds(250, 105, 120, 30);
    emoStatePanel.add(overallSkillLabel);

    setResizable(false);
    this.tabbedPane.setSelectedIndex(1);
    this.lowerTabbedPane.setSelectedIndex(1);
    startServer();
  }

  private void startServer() {
    Server server = new Server(Constants.URI, Constants.PORT, Constants.LINK, ServerWebSocket.class);
    try {
      server.start();
      System.out.println("Server started successfully...");

      for (Session client : ServerWebSocket.getClients()) {
        System.out.println("Session: " + client);
      }

      new Thread(new ServerThread(server)).start();
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
    String strText = sendButton.getText();
    if (worker == null)
      worker = new WorkerThread(timeTrackerLabel);

    worker.setButtonStatus(strText);
    worker.setInterval(Double.parseDouble(incrementDecrement.getText()));

    System.out.println("Text: " + Double.parseDouble(incrementDecrement.getText()));

    if (isAutoResetChecked) {
      if (strText.equalsIgnoreCase("Start")) {
        sendButton.setText("Stop");
        autoResetCheckBox.setEnabled(false);
      } else {
        sendButton.setText("Start");
        autoResetCheckBox.setEnabled(true);
      }
    }

    workerThread = new Thread(worker);
    workerThread.start();
  }

  public void setTimeTracker(String str) {
    timeTrackerLabel.setText(str);
  }

  private synchronized void closeThread() {
    notify();
  }

  @Override
  public void windowOpened(WindowEvent e) {
  }

  @Override
  public void windowClosing(WindowEvent e) {
    closeThread();
    System.out.println("Server is closing...");
    System.exit(0);
  }

  @Override
  public void windowClosed(WindowEvent e) {
  }

  @Override
  public void windowIconified(WindowEvent e) {
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
  }

  @Override
  public void windowActivated(WindowEvent e) {
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
  }
}
