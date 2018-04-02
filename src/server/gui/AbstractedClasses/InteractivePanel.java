package server.gui;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import server.sys.WorkerThread;
import util.ConsolePanel;
import util.UpDownButton;

public class InteractivePanel extends JPanel{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private boolean isAutoResetChecked = false;
  private static WorkerThread worker;
  private static Thread workerThread;
  
  private JLabel playerLabel;
  private JLabel emoStateLabel;
  private JLabel secLabel;
  private JButton sendButton;
  private JCheckBox autoResetCheckBox;
  private JComboBox<String> playerComboBox;
  private UpDownButton incrementDecrement;
  private ConsolePanel consolePanel;
  
  public InteractivePanel() {
    initialize();
    setLayout(null);
  }
  
  private void initialize() {
    
    playerLabel = new JLabel("Player");
    playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    playerLabel.setBounds(10, 10, 60, 15);
    
    emoStateLabel = new JLabel("<html>EmoState<br>Interval:</html>");
    emoStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emoStateLabel.setBounds(180, 10, 60, 30);
    
    secLabel = new JLabel("Sec");
    secLabel.setBounds(370, 15, 30, 15);
    
   sendButton = new JButton("Send");
    sendButton.setBounds(330, 55, 100, 30);
    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        handleStartStopSend();
      }
    });
    
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
    
    
    playerComboBox = new JComboBox<String>();
    playerComboBox.setModel(new DefaultComboBoxModel(new String[] { "0", "1" }));
    playerComboBox.setBounds(65, 10, 80, 30);
    
    incrementDecrement = new UpDownButton(110, 0.5, true);
    incrementDecrement.setLocation(250, 10);
    incrementDecrement.setOutputText("0.25");
    

      
    add(secLabel);
    add(playerLabel);
    add(emoStateLabel);
    add(sendButton);
    add(autoResetCheckBox);
    add(playerComboBox);
  }

  protected void handleStartStopSend() {
  String strText = sendButton.getText();
    if (worker == null)
      worker = new WorkerThread(timeTrackerLabel, consolePanel);

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
}