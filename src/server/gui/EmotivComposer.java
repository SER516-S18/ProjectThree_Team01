package server.gui;

import java.awt.EventQueue;
import java.time.LocalTime;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.websocket.DeploymentException;

import server.sys.ServerWebSocket;
import util.ConsolePanel;
import util.Constants;
import util.UpDownButton;

public class EmotivComposer extends JFrame {

  private static final long serialVersionUID = 6196061116172281774L;
  private JPanel contentPane;
  private ConsolePanel consolePanel;
  private JTabbedPane tabbedPane;
  private JMenuBar menuBar;
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

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          EmotivComposer frame = new EmotivComposer();
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
  public EmotivComposer() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 650);

    menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu mnDropDown = new JMenu("Drop Down");
    menuBar.add(mnDropDown);
    contentPane = new JPanel();
    contentPane.setBackground(Constants.SALMON);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    startPanel = new JPanel();
    startPanel.setBounds(2, 0, 444, 125);
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

    sendButton = new JButton("Send");
    sendButton.setBounds(330, 55, 100, 30);
    interactive.add(sendButton);

    autoResetCheckBox = new JCheckBox("Auto Reset");
    autoResetCheckBox.setBounds(170, 55, 115, 18);
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
    detectionPanel.setBounds(2, 150, 444, 438);
    contentPane.add(detectionPanel);
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
}
