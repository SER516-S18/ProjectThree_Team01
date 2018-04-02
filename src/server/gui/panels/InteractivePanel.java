package server.gui.panels;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.sys.WorkerThread;
import util.ConsolePanel;
import util.UpDownButton;

public class InteractivePanel extends JPanel{
  
  private static final long serialVersionUID = 8249772239761365173L;
  
  private JLabel playerLabel;
  private JLabel emoStateLabel;
  private JLabel secLabel;

  private JButton sendButton;
  private JCheckBox autoResetCheckBox;
  private JComboBox<String> playerComboBox;
  private UpDownButton emoStateInterval;
  
  public InteractivePanel() {
    setBounds(0, 0, 435, 90);
    setLayout(null);
    initialize();
  }
  
  private void initialize() {
    
    playerLabel = new JLabel("Player");
    playerLabel.setVerticalAlignment(SwingConstants.TOP);
    playerLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    playerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    playerLabel.setBounds(10, 10, 55, 30);
    
    emoStateLabel = new JLabel("<html>EmoState<br>Interval:</html>");
    emoStateLabel.setHorizontalAlignment(SwingConstants.CENTER);
    emoStateLabel.setBounds(180, 10, 60, 30);
    
    secLabel = new JLabel("Sec");
    secLabel.setVerticalAlignment(SwingConstants.TOP);
    secLabel.setBounds(370, 15, 30, 30);
    
   sendButton = new JButton("Send");
    sendButton.setBounds(330, 55, 100, 30);
    sendButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        EmotivComposer.handleStartStopSend(sendButton.getText());
      }
    });
    
    autoResetCheckBox = new JCheckBox("Auto Reset");
    autoResetCheckBox.setVerticalAlignment(SwingConstants.TOP);
    autoResetCheckBox.setBounds(170, 55, 115, 30);
    autoResetCheckBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        System.out.println("Checked: " + autoResetCheckBox.isSelected());
        if (autoResetCheckBox.isSelected()) {
          sendButton.setText("Start");
          EmotivComposer.isAutoResetChecked = true;
        } else {
          sendButton.setText("Send");
          EmotivComposer.isAutoResetChecked = false;
        }
      }
    });
        
    playerComboBox = new JComboBox<String>();
    playerComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1" }));
    playerComboBox.setBounds(65, 10, 80, 30);
    
    emoStateInterval = new UpDownButton(110, 0.5, true);
    emoStateInterval.setLocation(250, 10);
    emoStateInterval.setOutputText("0.25");
      
    add(secLabel);
    add(playerLabel);
    add(emoStateLabel);
    add(sendButton);
    add(autoResetCheckBox);
    add(playerComboBox);
    add(emoStateInterval);
  }
  
  public String getOutputText() {
    return this.emoStateInterval.getOutputText();
  }
  
  public void setInteractiveFields(String text, boolean isAutoReset) {
    autoResetCheckBox.setEnabled(isAutoReset);
    sendButton.setText(text);
  }
}