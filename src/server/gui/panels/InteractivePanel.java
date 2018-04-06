package server.gui.panels;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.gui.EmotivComposer;
import server.gui.actions.ActionEvents;
import server.gui.actions.ItemEvents;

/**
 * This class's purpose is to display the interactive tab section items
 * 
 * @author Vihar Bhatt
 * @author Cephas Armstrong-Mensah
 * @version 1.0
 * @since 02APR2018
 *
 */
public class InteractivePanel extends JPanel {

  private static final long serialVersionUID = 8249772239761365173L;

  private JLabel playerLabel;
  private JLabel emoStateLabel;
  private JLabel secLabel;

  private JButton sendButton;
  private JCheckBox autoResetCheckBox;
  private JComboBox<String> playerComboBox;
  private ComboControl emoStateInterval;

  public InteractivePanel() {
    setBounds(0, 0, 450, 90);
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
    sendButton.addActionListener(new ActionEvents(this));

    autoResetCheckBox = new JCheckBox("Auto Reset");
    autoResetCheckBox.setVerticalAlignment(SwingConstants.TOP);
    autoResetCheckBox.setBounds(170, 55, 115, 30);
    autoResetCheckBox.addItemListener(new ItemEvents(this));

    playerComboBox = new JComboBox<String>();
    playerComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "0", "1" }));
    playerComboBox.setBounds(65, 10, 80, 30);

    emoStateInterval = new ComboControl(110, 0.5, true);
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

  public void triggerStartStopSend() {
    EmotivComposer.handleStartStopSend(sendButton.getText());
    
    
    // for eye section auto reset button
    if(EmotivComposer.getemoFacialPanel().ischckbxNewCheckBoxSelected()) {
    EmotivComposer.getemoFacialPanel().getEyeActive().doClick();
    EmotivComposer.getemoFacialPanel().seteyeActiveValue(0);
    }
    
  }

  public void itemStateAction() {
    if (autoResetCheckBox.isSelected()) {
      sendButton.setText("Start");
      EmotivComposer.isAutoResetChecked = true;
      //
     // EmotivComposer.getemoFacialPanel().replaceRadioButton();
    } else {
      sendButton.setText("Send");
      EmotivComposer.isAutoResetChecked = false;
    }
    
    // replace a eye button with radiobutton
    if (autoResetCheckBox.isSelected()) {
      EmotivComposer.getemoFacialPanel().replaceRadioButton();
    }else {
      EmotivComposer.getemoFacialPanel().replacebackRadio();
    }
  }
}