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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
